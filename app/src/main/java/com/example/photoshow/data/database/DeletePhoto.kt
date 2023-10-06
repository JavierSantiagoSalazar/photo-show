package com.example.photoshow.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DeletePhoto(@PrimaryKey val idToDelete: Int)
