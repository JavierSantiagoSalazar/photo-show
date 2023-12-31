package com.example.photoshow.ui.common

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.domain.Error
import com.example.domain.Error.Connectivity
import com.example.domain.Error.Server
import com.example.domain.Error.Unknown
import com.example.photoshow.R
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch


inline fun <T : Any> basicDiffUtil(
    crossinline areItemsTheSame: (T, T) -> Boolean = { old, new -> old == new },
    crossinline areContentsTheSame: (T, T) -> Boolean = { old, new -> old == new }
) = object : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean =
        areItemsTheSame(oldItem, newItem)

    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean =
        areContentsTheSame(oldItem, newItem)
}

fun ImageView.loadUrl(url: String) {
    Glide.with(context).load(url)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(this)
}

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = true): View =
    LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)

fun <T, U> Fragment.diff(flow: Flow<T>, mapFun: (T) -> U, body: (U) -> Unit) {
    viewLifecycleOwner.launchAndCollect(
        flow = flow.map(mapFun).distinctUntilChanged(),
        body = body
    )
}

fun <T> LifecycleOwner.launchAndCollect(
    flow: Flow<T>,
    state: Lifecycle.State = Lifecycle.State.STARTED,
    body: (T) -> Unit
) {
    lifecycleScope.launch {
        this@launchAndCollect.repeatOnLifecycle(state) {
            flow.collect(body)
        }
    }
}

fun View.showErrorSnackBar(error: Error, action: () -> Unit) {
    when (error) {
        is Connectivity -> {
            this.showNoInternetConnectionSnackBar(this.context.errorToString(error)) {
                action()
            }
        }

        else -> this.showSnackBar(this.context.errorToString(error))
    }
}


fun Context.errorToString(error: Error) = when (error) {
    Connectivity -> getString(R.string.connectivity_error)
    is Server -> getString(R.string.server_error) + error.code
    is Unknown -> getString(R.string.unknown_error) + error.message
}

fun View.showNoInternetConnectionSnackBar(
    message: String,
    isIndefinite: Boolean = true,
    tryAgain: () -> Unit
) {
    val duration = if (isIndefinite) Snackbar.LENGTH_INDEFINITE else Snackbar.LENGTH_LONG
    Snackbar.make(this, message, duration).apply {
        setAction(R.string.snack_bar_try_again_message) {
            tryAgain()
            dismiss()
        }
        show()
    }
}

fun View.showSnackBar(
    message: String?,
    duration: Int = Snackbar.LENGTH_SHORT,
) {
    Snackbar.make(this, message ?: "", duration).apply {
        setAction(R.string.snack_bar_dismiss_message) {
            dismiss()
        }
        show()
    }
}

fun View.setVisibleOrGone(visible: Boolean) {
    visibility = if (visible) View.VISIBLE else View.INVISIBLE
}

fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Context.showConfirmationDialog(onConfirm: () -> Unit) {
    AlertDialog.Builder(this)
        .setTitle(R.string.alert_title)
        .setMessage(R.string.alert_message)
        .setPositiveButton(R.string.alert_option_yes) { dialog, _ ->
            onConfirm()
            dialog.dismiss()
        }
        .setNegativeButton(R.string.alert_option_no) { dialog, _ ->
            dialog.dismiss()
        }
        .create()
        .show()
}