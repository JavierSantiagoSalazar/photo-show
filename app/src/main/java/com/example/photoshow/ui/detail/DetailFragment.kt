package com.example.photoshow.ui.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.domain.Photo
import com.example.photoshow.R
import com.example.photoshow.databinding.FragmentDetailBinding
import com.example.photoshow.ui.common.launchAndCollect
import com.example.photoshow.ui.common.loadUrl
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.fragment_detail) {

    private val viewModel: DetailViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentDetailBinding.bind(view)

        with(binding) {
            binding.toolbar.setNavigationOnClickListener { findNavController().popBackStack() }
            fabDelete.setOnClickListener {  }
        }

        viewLifecycleOwner.launchAndCollect(viewModel.state) { state ->
            if (state.photo != null) {
                setDetailData(binding, state.photo)
            }
        }

    }

    private fun setDetailData(binding: FragmentDetailBinding, photo: Photo) {
        with(binding) {
            tvTitle.text = photo.title
            tvAlbumId.text = photo.albumId.toString()
            tvPhotoId.text = photo.id.toString()
            ivDetailPhoto.loadUrl(photo.photoUrl)
        }
    }
}
