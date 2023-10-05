package com.example.usecases

import com.example.data.repository.PhotosRepository
import com.example.domain.Photo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPhotosUseCase @Inject constructor(private val photosRepository: PhotosRepository) {

    operator fun invoke(): Flow<List<Photo>> {
        return photosRepository.photos
    }
}
