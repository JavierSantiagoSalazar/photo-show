package com.example.usecases

import com.example.data.repository.PhotosRepository
import com.example.domain.Error
import javax.inject.Inject

class DeletePhotosUseCase @Inject constructor(private val photosRepository: PhotosRepository) {

    suspend operator fun invoke(photosId: List<Int>): Error? {
        return photosRepository.deletePhoto(photosId)
    }
}
