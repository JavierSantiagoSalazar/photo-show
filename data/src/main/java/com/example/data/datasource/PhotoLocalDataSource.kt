package com.example.data.datasource

import com.example.domain.Photo
import kotlinx.coroutines.flow.Flow
import com.example.domain.Error

interface PhotoLocalDataSource {

    val photos: Flow<List<Photo>>

    val photosToDelete: Flow<List<Int>>

    suspend fun isEmpty(): Boolean

    suspend fun isIdsToDeleteEmpty(): Boolean

    suspend fun savePhotos(photos: List<Photo>): Error?

    fun findById(id: Int): Flow<Photo>

    suspend fun saveIdToDelete(photoId: Int): Error?

    suspend fun deletePhotosById(photosId: List<Int>): Error?

    suspend fun deletePhotosId(photosId: List<Int>): Error?
}
