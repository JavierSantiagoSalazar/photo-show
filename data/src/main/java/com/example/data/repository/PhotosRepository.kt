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

    fun findById(id: Int): Flow<Photo> = localDataSource.findById(id)

    suspend fun requestPhotos(): Error? {
        if (localDataSource.isEmpty()) {
            val movies = remoteDataSource.findPhotos()
            movies.fold(ifLeft = { return it }) {
                localDataSource.save(it)
            }
        }
        return null
    }

    suspend fun deletePhotoById(id: Int): Error? = localDataSource.deletePhotoById(id)

}
