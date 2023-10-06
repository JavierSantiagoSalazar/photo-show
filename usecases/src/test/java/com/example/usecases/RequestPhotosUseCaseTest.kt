package com.example.usecases

import com.example.data.repository.PhotosRepository
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
class RequestPhotosUseCaseTest{

    @Mock
    private lateinit var photosRepository: PhotosRepository

    private lateinit var requestPhotosUseCase: RequestPhotosUseCase

    @Before
    fun setup() {
        requestPhotosUseCase = RequestPhotosUseCase(photosRepository)
    }

    @Test
    fun `when invoke calls should call photosRepository`(): Unit = runTest {

        requestPhotosUseCase()

        verify(photosRepository).requestPhotos()
    }
}