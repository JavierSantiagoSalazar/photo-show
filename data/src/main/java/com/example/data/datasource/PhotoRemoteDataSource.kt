package com.example.data.datasource

import arrow.core.Either
import com.example.domain.Error
import com.example.domain.Photo

interface PhotoRemoteDataSource {
    suspend fun findPhotos(): Either<Error, List<Photo>>

    suspend fun deletePhotos(photosId: List<Int>): Error?

}
