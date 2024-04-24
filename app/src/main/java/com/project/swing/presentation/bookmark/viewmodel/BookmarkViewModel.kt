package com.project.swing.presentation.bookmark.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.swing.domain.model.UnsplashPhotoModel
import com.project.swing.domain.usecase.GetLikePhotosUseCase
import com.project.swing.domain.usecase.UnlikePhotoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class BookmarkViewModel @Inject constructor(
    private val getLikePhotosUseCase: GetLikePhotosUseCase,
    private val unlikePhotoUseCase: UnlikePhotoUseCase
) : ViewModel() {
    private val _getLikePhotosUiState = MutableStateFlow<GetLikePhotosUiState>(GetLikePhotosUiState.Loading)
    val getLikePhotosUiState = _getLikePhotosUiState.asStateFlow()

    private val _unlikePhotoUiState = MutableStateFlow<UnlikePhotoUiState>(UnlikePhotoUiState.Loading)
    val unlikePhotoUiState = _unlikePhotoUiState.asStateFlow()

    var photos = mutableStateListOf<UnsplashPhotoModel>()
        private set

    fun getLikePhotos(page: Int) = viewModelScope.launch {
        _getLikePhotosUiState.value = GetLikePhotosUiState.Loading
        getLikePhotosUseCase(page)
            .onSuccess {
                it.catch { remoteError ->
                    _getLikePhotosUiState.value = GetLikePhotosUiState.Error(remoteError)
                }.collect { result ->
                    _getLikePhotosUiState.value = GetLikePhotosUiState.Success(result)
                }
            }
            .onFailure {
                _getLikePhotosUiState.value = GetLikePhotosUiState.Error(it)
            }
    }

    fun unlikePhoto(id: String) = viewModelScope.launch {
        unlikePhotoUseCase(id)
            .onSuccess {
                it.catch { remoteError ->
                    _unlikePhotoUiState.value = UnlikePhotoUiState.Error(remoteError)
                }.collect { result ->
                    _unlikePhotoUiState.value = UnlikePhotoUiState.Success(result)
                }
            }
            .onFailure {
                _unlikePhotoUiState.value = UnlikePhotoUiState.Error(it)
            }
    }

    fun addPhotos(list: List<UnsplashPhotoModel>) {
        photos.addAll(list)
    }

    fun clearPhotos() = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            photos.clear()
        }
    }

}