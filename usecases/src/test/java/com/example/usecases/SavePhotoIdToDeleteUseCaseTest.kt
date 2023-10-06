package com.example.usecases

import com.example.data.repository.PhotosRepository
import com.example.photoshow.testshared.samplePhoto
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class SavePhotoIdToDeleteUseCaseTest{

    @Mock
    private lateinit var photosRepository: PhotosRepository

    private lateinit var savePhotoIdToDeleteUseCase: SavePhotoIdToDeleteUseCase

    @Before
    fun setup() {
        savePhotoIdToDeleteUseCase = SavePhotoIdToDeleteUseCase(photosRepository)
    }

    @Test
    fun `when invoke calls should call photosRepository`(): Unit = runTest {

        val photoId = samplePhoto.id

        savePhotoIdToDeleteUseCase(photoId)

        verify(photosRepository).savePhotoId(1)
    }
}