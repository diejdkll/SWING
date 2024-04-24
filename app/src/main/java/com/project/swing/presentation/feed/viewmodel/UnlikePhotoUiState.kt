package com.project.swing.presentation.feed.viewmodel

import com.project.swing.domain.model.LikePhotoResponseModel

sealed interface UnlikePhotoUiState {
    data object Loading : UnlikePhotoUiState
    data class Success(val data: LikePhotoResponseModel) : UnlikePhotoUiState
    data class Error(val exception: Throwable) : UnlikePhotoUiState
}