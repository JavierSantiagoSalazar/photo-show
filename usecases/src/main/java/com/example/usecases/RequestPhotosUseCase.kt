package com.example.usecases

import com.example.data.repository.PhotosRepository
import com.example.domain.Error
import javax.inject.Inject

class RequestPhotosUseCase @Inject constructor(private val photosRepository: PhotosRepository) {

    suspend operator fun invoke(): Error? {
        return photosRepository.requestPhotos()
    }
}
