package com.project.swing.presentation.bookmark.viewmodel

import com.project.swing.domain.model.UnsplashPhotoModel

sealed interface GetLikePhotosUiState {
    data object Loading : GetLikePhotosUiState
    data class Success(val data: List<UnsplashPhotoModel>) : GetLikePhotosUiState
    data class Error(val exception: Throwable) : GetLikePhotosUiState
}