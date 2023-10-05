package com.example.domain

data class Photo(
    val id: Int,
    val albumId: Int,
    val title: String,
    val photoUrl: String,
    val thumbnailPhotoUrl: String
)
