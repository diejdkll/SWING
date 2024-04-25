package com.project.bookmark.viewmodel

import com.project.model.UnsplashPhotoModel

sealed interface GetLikePhotosUiState {
    data object Loading : GetLikePhotosUiState
    data class Success(val data: List<UnsplashPhotoModel>) : GetLikePhotosUiState
    data class Error(val exception: Throwable) : GetLikePhotosUiState
    data object ResultEmpty : GetLikePhotosUiState
}