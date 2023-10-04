package com.example.photoshow.photos

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.photoshow.Photo
import com.example.photoshow.R
import com.example.photoshow.basicDiffUtil
import com.example.photoshow.databinding.ItemPhotoBinding
import com.example.photoshow.inflate
import com.example.photoshow.loadUrl

class PhotosAdapter :
    ListAdapter<Photo, PhotosAdapter.ViewHolder>(basicDiffUtil { old, new -> old.id == new.id }) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.item_photo)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val photo = getItem(position)
        holder.bind(photo)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemPhotoBinding.bind(view)
        fun bind(photo: Photo) = with(binding) {
            ivPhoto.loadUrl(photo.thumbnailPhotoUrl)
            tvPhotoTitle.text = photo.title
        }
    }
}
