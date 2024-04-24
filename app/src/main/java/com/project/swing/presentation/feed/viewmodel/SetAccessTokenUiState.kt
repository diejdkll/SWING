package com.project.swing.presentation.feed.viewmodel

sealed interface SetAccessTokenUiState {
    data object Loading : SetAccessTokenUiState
    data object Success : SetAccessTokenUiState
    data class Error(val exception: Throwable) : SetAccessTokenUiState
}