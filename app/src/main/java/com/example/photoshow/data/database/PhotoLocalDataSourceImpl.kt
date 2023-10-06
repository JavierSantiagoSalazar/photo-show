package com.example.photoshow.data.database

import com.example.data.datasource.PhotoLocalDataSource
import com.example.domain.Photo
import com.example.domain.Error
import com.example.photoshow.data.tryCall
import com.example.photoshow.data.database.Photo as DbPhoto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PhotoLocalDataSourceImpl @Inject constructor(
    private val photoDao: PhotoDao
) : PhotoLocalDataSource {

    override val photos: Flow<List<Photo>> = photoDao.getAllPhotos().map { it.toDomainModel() }

    override val photosToDelete: Flow<List<Int>> = photoDao.getPhotosIdsToDelete().map { it.toIntModel() }

    override suspend fun isEmpty(): Boolean = photoDao.photosCount() == 0

    override suspend fun isIdsToDeleteEmpty(): Boolean = photoDao.photosIdsCount() == 0

    override suspend fun savePhotos(photos: List<Photo>): Error? =
        tryCall {
            photoDao.insertPhotos(photos.fromDomainModel())
        }.fold(
            ifLeft = { it },
            ifRight = { null }
        )

    override fun findById(id: Int): Flow<Photo> =
        photoDao.findById(id).map { it.toDomainModel() }

    override suspend fun saveIdToDelete(photoId: Int): Error? =
        tryCall {
            photoDao.insertPhotoId(photoId.fromIntModel())
        }.fold(
            ifLeft = { it },
            ifRight = { null }
        )

    override suspend fun deletePhotosById(photosId: List<Int>): Error? =
        tryCall {
            photosId.map { photoId ->
                photoDao.deletePhotoById(photoId)
            }
        }.fold(
            ifLeft = { it },
            ifRight = { null }
        )

    override suspend fun deletePhotosId(photosId: List<Int>): Error? =
        tryCall {
            photosId.map { photoId ->
                photoDao.deletePhotoId(photoId)
            }
        }.fold(
            ifLeft = { it },
            ifRight = { null }
        )
}

private fun List<DbPhoto>.toDomainModel(): List<Photo> =
    map { it.toDomainModel() }

private fun DbPhoto.toDomainModel(): Photo =
    Photo(
        id = id,
        albumId = albumId,
        title = title,
        photoUrl = photoUrl,
        thumbnailPhotoUrl = thumbnailPhotoUrl
    )

private fun List<Photo>.fromDomainModel(): List<DbPhoto> =
    map { it.fromDomainModel() }

private fun Photo.fromDomainModel(): DbPhoto =
    DbPhoto(
        id = id,
        albumId = albumId,
        title = title,
        photoUrl = photoUrl,
        thumbnailPhotoUrl = thumbnailPhotoUrl
    )

private fun List<Int>.fromIntModel(): List<DeletePhoto> =
    map { it.fromIntModel() }

private fun Int.fromIntModel(): DeletePhoto =
    DeletePhoto(
        idToDelete = this
    )

private fun List<DeletePhoto>.toIntModel(): List<Int> =
    map { it.toIntModel() }

private fun DeletePhoto.toIntModel(): Int = idToDelete
