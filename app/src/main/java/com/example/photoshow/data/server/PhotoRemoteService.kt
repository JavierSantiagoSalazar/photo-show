package com.example.photoshow.data.server

import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path

interface PhotoRemoteService {

    @GET("photos")
    suspend fun listPhotos(): List<RemotePhoto>

    @DELETE("photos/{photoId}")
    suspend fun deletePhoto(@Path("photoId") photoId: Int)

}
