package com.project.swing.presentation.feed.viewmodel

import com.project.swing.domain.model.UnsplashResponseModel

sealed interface SearchPhotosUiState {
    data object Loading : SearchPhotosUiState
    data class Success(val data: UnsplashResponseModel) : SearchPhotosUiState
    data class Error(val exception: Throwable) : SearchPhotosUiState
    data object ResultEmpty : SearchPhotosUiState
    data object QueryEmpty : SearchPhotosUiState
}