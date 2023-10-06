package com.example.photoshow.apptestshared

import com.example.photoshow.data.server.RemotePhoto

fun buildRemotePhotos(vararg id: Int) = id.map {
    RemotePhoto(
        id = it,
        albumId = it,
        title = "Photo Title $it",
        photoUrl = "http://photourl.com/photo/1",
        thumbnailPhotoUrl = "http://thumnail.com/photo/1"
    )
}
