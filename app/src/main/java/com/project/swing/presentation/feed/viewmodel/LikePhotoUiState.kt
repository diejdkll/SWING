package com.project.swing.presentation.feed.viewmodel

import com.project.swing.domain.model.LikePhotoResponseModel

sealed interface LikePhotoUiState {
    data object Loading : LikePhotoUiState
    data class Success(val data: LikePhotoResponseModel) : LikePhotoUiState
    data class Error(val exception: Throwable) : LikePhotoUiState
}