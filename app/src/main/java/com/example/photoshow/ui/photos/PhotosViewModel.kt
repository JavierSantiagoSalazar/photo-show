package com.example.photoshow.ui.photos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.Error
import com.example.domain.Photo
import com.example.photoshow.data.toError
import com.example.usecases.GetPhotosUseCase
import com.example.usecases.RequestPhotosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhotosViewModel @Inject constructor(
    getPhotosUseCase: GetPhotosUseCase,
    private val requestPhotosUseCase: RequestPhotosUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            getPhotosUseCase()
                .catch { cause -> _state.update { it.copy(error = cause.toError()) } }
                .collect { photos -> _state.update { it.copy(photos = photos) } }
        }
    }

    fun getPhotos() {
        viewModelScope.launch {
            _state.value = _state.value.copy(loading = true)
            val error = requestPhotosUseCase()
            _state.update { _state.value.copy(loading = false, error = error) }
        }
    }

    data class UiState(
        val loading: Boolean = false,
        val photos: List<Photo>? = null,
        val error: Error? = null
    )
}
