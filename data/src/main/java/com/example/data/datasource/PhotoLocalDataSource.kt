package com.example.data.datasource

import com.example.domain.Photo
import kotlinx.coroutines.flow.Flow
import com.example.domain.Error

interface PhotoLocalDataSource {

    val photos: Flow<List<Photo>>

    suspend fun isEmpty(): Boolean

    suspend fun save(photos: List<Photo>): Error?

    fun findById(id: Int): Flow<Photo>

    suspend fun deletePhotoById(id: Int): Error?
}
