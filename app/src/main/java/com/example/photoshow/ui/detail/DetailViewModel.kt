package com.example.photoshow.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.Error
import com.example.domain.Photo
import com.example.photoshow.data.toError
import com.example.photoshow.di.annotations.PhotoId
import com.example.photoshow.ui.common.networkhelper.NetworkHelper
import com.example.usecases.DeletePhotosUseCase
import com.example.usecases.FindPhotoUseCase
import com.example.usecases.SavePhotoIdToDeleteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DetailViewModel @Inject constructor(
    @PhotoId private val photoId: Int,
    findMovieUseCase: FindPhotoUseCase,
    private val networkHelper: NetworkHelper,
    private val deletePhotosUseCase: DeletePhotosUseCase,
    private val savePhotoIdToDeleteUseCase: SavePhotoIdToDeleteUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    init {
        _state.value = _state.value.copy(isSuccessfullyDeleted = false, isSuccessfullySaved = false)
        viewModelScope.launch {
            findMovieUseCase(photoId)
                .catch { cause -> _state.update { it.copy(error = cause.toError()) } }
                .collect { photo -> _state.update { UiState(photo = photo) } }
        }
    }

    fun deletePhotoOrSavePhotoId(photoId: Int) {
        val isInternetAvailable = networkHelper.isInternetAvailable()
        viewModelScope.launch {

            val error = if (isInternetAvailable) {
                deletePhotosUseCase(listOf(photoId))
            } else {
                savePhotoIdToDeleteUseCase(photoId)
            }

            if (error == null && !isInternetAvailable) {
                _state.value = _state.value.copy(isSuccessfullySaved = true)
            } else if (error == null) {
                _state.value = _state.value.copy(isSuccessfullyDeleted = true)
            }
            _state.update { _state.value.copy(error = error) }
        }
    }
}

data class UiState(
    val photo: Photo? = null,
    val error: Error? = null,
    val isSuccessfullyDeleted: Boolean = false,
    val isSuccessfullySaved: Boolean = false,
)

