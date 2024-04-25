package com.project.bookmark.viewmodel

import com.project.model.LikePhotoResponseModel

sealed interface UnlikePhotoUiState {
    data object Loading : UnlikePhotoUiState
    data class Success(val data: LikePhotoResponseModel) : UnlikePhotoUiState
    data class Error(val exception: Throwable) : UnlikePhotoUiState
}