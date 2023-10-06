package com.example.photoshow.ui.detail

import app.cash.turbine.test
import com.example.photoshow.testrules.CoroutinesTestRule
import com.example.photoshow.testshared.samplePhoto
import com.example.photoshow.ui.common.networkhelper.NetworkHelper
import com.example.usecases.DeletePhotosUseCase
import com.example.usecases.FindPhotoUseCase
import com.example.usecases.SavePhotoIdToDeleteUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @Mock
    lateinit var findPhotoUseCase: FindPhotoUseCase

    @Mock
    lateinit var deletePhotoUseCase: DeletePhotosUseCase

    @Mock
    lateinit var networkHelper: NetworkHelper

    @Mock
    lateinit var savePhotoIdToDeleteUseCase: SavePhotoIdToDeleteUseCase

    private lateinit var vm: DetailViewModel

    private val photo = samplePhoto.copy(id = 2)

    @Before
    fun setup() {
        whenever(findPhotoUseCase(2)).thenReturn(flowOf(photo))
        vm = DetailViewModel(
            photoId = 2,
            findPhotoUseCase = findPhotoUseCase,
            networkHelper = networkHelper,
            deletePhotosUseCase = deletePhotoUseCase,
            savePhotoIdToDeleteUseCase = savePhotoIdToDeleteUseCase
        )
    }

    @Test
    fun `UI is updated with the photo on start`() = runTest {
        vm.state.test {
            assertEquals(UiState(), awaitItem())
            assertEquals(UiState(photo = photo), awaitItem())
            cancel()
        }
    }

    @Test
    fun `Given internet connection verify that Delete action calls the corresponding use case`() = runTest {

        whenever(networkHelper.isInternetAvailable()).thenReturn(true)

        vm.deletePhotoOrSavePhotoId(photo.id)
        runCurrent()

        verify(deletePhotoUseCase).invoke(listOf(photo.id))
    }

    @Test
    fun `Given no internet connection verify that Delete action calls the corresponding use case`() = runTest {

        whenever(networkHelper.isInternetAvailable()).thenReturn(false)

        vm.deletePhotoOrSavePhotoId(photo.id)
        runCurrent()

        verify(savePhotoIdToDeleteUseCase).invoke(photo.id)
    }
}
