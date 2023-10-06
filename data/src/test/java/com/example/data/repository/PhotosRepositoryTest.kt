package com.example.data.repository

import arrow.core.right
import com.example.data.datasource.PhotoLocalDataSource
import com.example.data.datasource.PhotoRemoteDataSource
import com.example.photoshow.testshared.sampleDeletePhotosIds
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
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class PhotosRepositoryTest {

    @Mock
    lateinit var localDataSource: PhotoLocalDataSource

    @Mock
    lateinit var remoteDataSource: PhotoRemoteDataSource

    private lateinit var photosRepository: PhotosRepository

    @Before
    fun setup(){
        photosRepository = PhotosRepository(remoteDataSource, localDataSource)
    }

    @Test
    fun `Photos are taken from local data source if is available`(): Unit = runTest {

        val localPhotos = flowOf(listOf(samplePhoto.copy(2)))
        whenever(localDataSource.photos).thenReturn(localPhotos)

        val result = photosRepository.photos

        assertEquals(localPhotos, result)
    }

    @Test
    fun `Photos are saved to local data source when it's empty`(): Unit = runTest {
        val remotePhotos = listOf(samplePhoto.copy(2))
        whenever(localDataSource.isEmpty()).thenReturn(true)
        whenever(remoteDataSource.findPhotos()).thenReturn(remotePhotos.right())

        photosRepository.requestPhotos()

        verify(localDataSource).savePhotos(remotePhotos)
    }

    @Test
    fun `deletePhotosIds are taken from local data source if is available`(): Unit = runTest {

        val localDeletePhotosIds =  flowOf(sampleDeletePhotosIds)
        whenever(localDataSource.photosToDelete).thenReturn(localDeletePhotosIds)

        val result = photosRepository.photosToDelete

        assertEquals(localDeletePhotosIds, result)
    }
}
