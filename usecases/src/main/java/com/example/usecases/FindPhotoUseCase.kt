package com.example.usecases

import com.example.data.repository.PhotosRepository
import com.example.domain.Photo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FindPhotoUseCase @Inject constructor(private val repository: PhotosRepository) {

    operator fun invoke(id: Int): Flow<Photo> = repository.findById(id)
}
