package com.example.photoshow.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Photo::class, DeletePhoto::class], version = 1, exportSchema = false)
abstract class PhotoShowDatabase : RoomDatabase() {

    abstract fun photoDao(): PhotoDao
}
