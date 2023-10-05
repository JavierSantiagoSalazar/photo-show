package com.example.photoshow.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.Error
import com.example.domain.Photo
import com.example.photoshow.data.toError
import com.example.photoshow.di.annotations.PhotoId
import com.example.usecases.FindPhotoUseCase
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
) : ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
                findMovieUseCase(photoId)
                    .catch { cause -> _state.update { it.copy(error = cause.toError()) } }
                    .collect { photo -> _state.update { UiState(photo = photo) } }
        }
    }

    data class UiState(
        val photo: Photo? = null,
        val error: Error? = null
    )
}
