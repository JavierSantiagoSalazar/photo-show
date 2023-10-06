package com.example.usecases

import com.example.data.repository.PhotosRepository
import com.example.domain.Photo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class GetPhotosIdsToDeleteUseCase @Inject constructor(private val photosRepository: PhotosRepository) {

    suspend operator fun invoke(): Flow<List<Int>> {
        return if (!photosRepository.isIdsToDeleteEmpty()) {
            photosRepository.photosToDelete
        } else {
            flowOf(emptyList())
        }
    }
}
