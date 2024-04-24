package com.project.swing.presentation.feed.viewmodel

sealed interface RequestAccessTokenUiState {
    data object Loading : RequestAccessTokenUiState
    data object Success : RequestAccessTokenUiState
    data class Error(val exception: Throwable) : RequestAccessTokenUiState
}