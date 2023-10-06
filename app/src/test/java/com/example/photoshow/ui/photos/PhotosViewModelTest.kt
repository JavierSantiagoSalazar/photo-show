package com.example.photoshow.ui.photos

import app.cash.turbine.test
import com.example.photoshow.testrules.CoroutinesTestRule
import com.example.photoshow.testshared.sampleDeletePhotosIds
import com.example.photoshow.testshared.samplePhoto
import com.example.photoshow.ui.photos.PhotosViewModel.*
import com.example.usecases.DeletePhotosUseCase
import com.example.usecases.GetPhotosIdsToDeleteUseCase
import com.example.usecases.GetPhotosUseCase
import com.example.usecases.RequestPhotosUseCase
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
class PhotosViewModelTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @Mock
    lateinit var getPhotosUseCase: GetPhotosUseCase

    @Mock
    lateinit var getPhotosIdsToDeleteUseCase: GetPhotosIdsToDeleteUseCase

    @Mock
    lateinit var deletePhotosUseCase: DeletePhotosUseCase

    @Mock
    lateinit var requestPhotosUseCase: RequestPhotosUseCase

    private lateinit var vm: PhotosViewModel

    private val photos = listOf(samplePhoto.copy(2))

    @Before
    fun setup() {
        whenever(getPhotosUseCase()).thenReturn(flowOf(photos))
        vm = PhotosViewModel(
            getPhotosUseCase = getPhotosUseCase,
            getPhotosIdsToDeleteUseCase = getPhotosIdsToDeleteUseCase,
            deletePhotosUseCase = deletePhotosUseCase,
            requestPhotosUseCase = requestPhotosUseCase
        )
    }

    @Test
    fun `State is updated with current cached content immediately`() = runTest {
        vm.state.test {
            assertEquals(UiState(), awaitItem())
            assertEquals(UiState(photos = photos), awaitItem())
            cancel()
        }
    }

    @Test
    fun `Progress is shown when GetPhotosUseCase is called and hidden when it finishes requesting photos`() =
        runTest {
            vm.getPhotos()

            vm.state.test {
                assertEquals(UiState(), awaitItem())
                assertEquals(UiState(photos = photos), awaitItem())
                assertEquals(
                    UiState(photos = photos, loading = true),
                    awaitItem()
                )
                assertEquals(
                    UiState(photos = photos, loading = false),
                    awaitItem()
                )
                cancel()
            }
        }

    @Test
    fun `WereSuccessfullyDeleted value change when DeletePhotosUseCase is called`() =
        runTest {

            whenever(getPhotosIdsToDeleteUseCase()).thenReturn(flowOf(sampleDeletePhotosIds))

            vm.deleteStackedPhotos()

            vm.state.test {
                assertEquals(UiState(), awaitItem())
                assertEquals(UiState(photos = photos), awaitItem())
                assertEquals(
                    UiState(photos = photos, loading = true),
                    awaitItem()
                )
                assertEquals(
                    UiState(photos = photos, loading = true, wereSuccessfullyDeleted = true),
                    awaitItem()
                )
                cancelAndIgnoreRemainingEvents()
            }
        }

    @Test
    fun `Photos are requested when GetPhotosUseCase starts`() = runTest {
        vm.getPhotos()
        runCurrent()

        verify(requestPhotosUseCase).invoke()
    }

    @Test
    fun `IdsToDelete are requested when GetPhotosIdsToDeleteUseCase starts`() = runTest {

        whenever(getPhotosIdsToDeleteUseCase()).thenReturn(flowOf(sampleDeletePhotosIds))

        vm.deleteStackedPhotos()
        runCurrent()

        verify(deletePhotosUseCase).invoke(sampleDeletePhotosIds)
    }
}
