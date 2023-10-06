package com.example.usecases

import com.example.data.repository.PhotosRepository
import com.example.photoshow.testshared.sampleDeletePhotosIds
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class DeletePhotosUseCaseTest {

    @Mock
    private lateinit var photosRepository: PhotosRepository

    private lateinit var deletePhotosUseCase: DeletePhotosUseCase

    @Before
    fun setup() {
        deletePhotosUseCase = DeletePhotosUseCase(photosRepository)
    }

    @Test
    fun `when invoke calls should call photosRepository`(): Unit = runTest {

        val photosToDeleteIds = sampleDeletePhotosIds

        deletePhotosUseCase(photosToDeleteIds)

        verify(photosRepository).deletePhoto(photosToDeleteIds)

    }
}