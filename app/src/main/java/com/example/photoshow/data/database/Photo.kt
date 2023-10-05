package com.example.photoshow.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Photo(
    @PrimaryKey val id: Int,
    val albumId: Int,
    val title: String,
    val photoUrl: String,
    val thumbnailPhotoUrl: String
)
