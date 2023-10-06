package com.example.usecases

import com.example.data.repository.PhotosRepository
import com.example.photoshow.testshared.sampleDeletePhotosIds
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
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
class GetPhotosIdsToDeleteUseCaseTest {

    @Mock
    private lateinit var photosRepository: PhotosRepository

    private lateinit var getPhotosIdsToDeleteUseCase: GetPhotosIdsToDeleteUseCase

    @Before
    fun setup() {
        getPhotosIdsToDeleteUseCase = GetPhotosIdsToDeleteUseCase(photosRepository)
    }

    @Test
    fun `Given a fully DeleteIdsEntity when GetPhotosIdsToDeleteUseCase is called should returns photosToDeleteIds`(): Unit = runTest {

        val photosToDeleteIds = flowOf(sampleDeletePhotosIds)

        whenever(photosRepository.photosToDelete).thenReturn(photosToDeleteIds)
        whenever(photosRepository.isIdsToDeleteEmpty()).thenReturn(false)


        val result = getPhotosIdsToDeleteUseCase()

        assertEquals(photosToDeleteIds, result)
    }

    @Test
    fun `Given a empty DeleteIdsEntity when GetPhotosIdsToDeleteUseCase is called should returns emptyList`(): Unit = runTest {

        val photosToDeleteIds = flowOf(emptyList<Int>())

        whenever(photosRepository.isIdsToDeleteEmpty()).thenReturn(true)

        val result = getPhotosIdsToDeleteUseCase()

        assertEquals(photosToDeleteIds.toList(), result.toList())
    }
}
