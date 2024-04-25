package com.project.feed.viewmodel

import com.project.model.LikePhotoResponseModel

sealed interface LikePhotoUiState {
    data object Loading : LikePhotoUiState
    data class Success(val data: LikePhotoResponseModel) : LikePhotoUiState
    data class Error(val exception: Throwable) : LikePhotoUiState
}