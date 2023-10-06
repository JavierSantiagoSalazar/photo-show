package com.example.photoshow.apptestshared

import com.example.photoshow.data.server.PhotoRemoteService
import com.example.photoshow.data.server.RemotePhoto

class FakePhotoRemoteService(
    private val photos: List<RemotePhoto> = emptyList(),
) : PhotoRemoteService {

    override suspend fun listPhotos(): List<RemotePhoto> = photos

    override suspend fun deletePhoto(photoId: Int) = Unit
}