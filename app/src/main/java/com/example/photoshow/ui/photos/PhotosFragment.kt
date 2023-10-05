package com.example.photoshow.ui.photos

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.domain.Photo
import com.example.photoshow.R
import com.example.photoshow.databinding.FragmentPhotosBinding
import com.example.photoshow.ui.common.diff
import com.example.photoshow.ui.common.launchAndCollect
import com.example.photoshow.ui.common.setVisibleOrGone
import com.example.photoshow.ui.common.showErrorSnackBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PhotosFragment : Fragment(R.layout.fragment_photos) {

    private val viewModel: PhotosViewModel by viewModels()

    private val adapter = PhotosAdapter{ onPhotoClicked(it) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentPhotosBinding.bind(view).apply { recyclerPhotos.adapter = adapter }

        with(viewModel.state) {
            diff(this, { it.loading }) {
                it.let { binding.progress.setVisibleOrGone(it) }
            }
            diff(this, { it.photos }) { photos ->
                adapter.submitList(photos?.take(1000))
            }
            launchAndCollect(this) {
                it.error?.let { error ->
                    if (view.isShown) {
                        view.showErrorSnackBar(error) {
                            viewModel.getPhotos()
                        }
                    }
                }
            }
        }

        viewModel.getPhotos()
    }

    private fun onPhotoClicked(photo: Photo) {
        val action = PhotosFragmentDirections.actionPhotosToDetail(photo.id)
        findNavController().navigate(action)
    }

}