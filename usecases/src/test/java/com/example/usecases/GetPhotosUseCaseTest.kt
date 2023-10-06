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
class GetPhotosUseCaseTest {

    @Mock
    private lateinit var photosRepository: PhotosRepository

    private lateinit var getPhotosUseCase: GetPhotosUseCase

    @Before
    fun setup() {
        getPhotosUseCase = GetPhotosUseCase(photosRepository)
    }

    @Test
    fun `when GetPhotosUseCase is called should returns photos`(): Unit = runTest {

        val photos =
            flowOf(listOf(samplePhoto.copy(2)))

        whenever(photosRepository.photos).thenReturn(photos)


        val result = getPhotosUseCase()

        assertEquals(photos, result)
    }
}
