package com.example.photoshow.data.server

import retrofit2.http.GET

interface PhotoRemoteService {

    @GET("photos")
    suspend fun listPhotos(): List<RemotePhoto>

}
