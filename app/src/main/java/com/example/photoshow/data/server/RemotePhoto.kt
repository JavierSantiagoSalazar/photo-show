package com.example.photoshow.data.server

import com.google.gson.annotations.SerializedName

data class RemotePhoto(
    val albumId: Int,
    val id: Int,
    val title: String,
    @SerializedName("url") val photoUrl: String,
    @SerializedName("thumbnailUrl") val thumbnailPhotoUrl: String,
)
