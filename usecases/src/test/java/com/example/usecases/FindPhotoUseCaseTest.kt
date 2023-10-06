package com.example.usecases

import com.example.data.repository.PhotosRepository
import com.example.photoshow.testshared.samplePhoto
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class FindPhotoUseCaseTest {

    @Mock
    private lateinit var photosRepository: PhotosRepository

    private lateinit var findPhotoUseCase: FindPhotoUseCase

    @Before
    fun setup() {
        findPhotoUseCase = FindPhotoUseCase(photosRepository)
    }

    @Test
    fun `Invoke calls photo repository and returns a photo`(): Unit = runTest {
        val photoFlow = flowOf(samplePhoto)

        whenever(photosRepository.findById(samplePhoto.id)).thenReturn(photoFlow)

        val result = findPhotoUseCase(samplePhoto.id)

        assertEquals(photoFlow, result)
    }
}