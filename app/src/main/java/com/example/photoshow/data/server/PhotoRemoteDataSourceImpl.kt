package com.example.photoshow.data.server

import arrow.core.Either
import com.example.data.datasource.PhotoRemoteDataSource
import com.example.domain.Error
import com.example.domain.Photo
import com.example.photoshow.data.toError
import com.example.photoshow.data.tryCall
import javax.inject.Inject

class PhotoRemoteDataSourceImpl @Inject constructor(
    private val photoRemoteService: PhotoRemoteService,
) : PhotoRemoteDataSource {

    override suspend fun findPhotos(): Either<Error, List<Photo>> = tryCall {
        photoRemoteService
            .listPhotos()
            .toDomainModel()
    }

    override suspend fun deletePhotos(photosId: List<Int>): Error? {
        try {
            photosId.map { photoId ->
                photoRemoteService
                    .deletePhoto(photoId)
            }
        } catch (e: Exception) {
            return e.toError()
        }
        return null
    }
}

private fun List<RemotePhoto>.toDomainModel(): List<Photo> =
    map { it.toDomainModel() }

private fun RemotePhoto.toDomainModel(): Photo =
    Photo(
        id = id,
        albumId = albumId,
        title = title,
        photoUrl = photoUrl,
        thumbnailPhotoUrl = thumbnailPhotoUrl
    )
