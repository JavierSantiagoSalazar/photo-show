package com.example.data.repository

import com.example.data.datasource.PhotoLocalDataSource
import com.example.data.datasource.PhotoRemoteDataSource
import com.example.domain.Photo
import com.example.domain.Error
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PhotosRepository @Inject constructor(
    private val remoteDataSource: PhotoRemoteDataSource,
    private val localDataSource: PhotoLocalDataSource,
) {

    val photos get() = localDataSource.photos

    val photosToDelete get() = localDataSource.photosToDelete

    fun findById(id: Int): Flow<Photo> = localDataSource.findById(id)

    suspend fun requestPhotos(): Error? {
        if (localDataSource.isEmpty()) {
            val movies = remoteDataSource.findPhotos()
            movies.fold(ifLeft = { return it }) {
                localDataSource.savePhotos(it)
            }
        }
        return null
    }

    suspend fun savePhotoId(photoId: Int): Error? {
        return localDataSource.saveIdToDelete(photoId)
    }

    suspend fun isIdsToDeleteEmpty(): Boolean {
        return localDataSource.isIdsToDeleteEmpty()
    }

    suspend fun deletePhoto(photosId: List<Int>): Error? {
        return remoteDataSource.deletePhotos(photosId) ?: deleteLocalPhoto(photosId)
    }

    private suspend fun deleteLocalPhoto(photosId: List<Int>): Error? {
        return localDataSource.deletePhotosById(photosId)
            ?: localDataSource.deletePhotosId(photosId)
    }
}
