package com.project.swing.presentation.feed.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.swing.domain.model.UnsplashPhotoModel
import com.project.swing.domain.usecase.GetAccessTokenUseCase
import com.project.swing.domain.usecase.LikePhotoUseCase
import com.project.swing.domain.usecase.RequestAccessTokenUseCase
import com.project.swing.domain.usecase.SearchPhotosUseCase
import com.project.swing.domain.usecase.SetAccessTokenUseCase
import com.project.swing.domain.usecase.UnlikePhotoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FeedViewModel @Inject constructor(
    private val searchPhotosUseCase: SearchPhotosUseCase,
    private val likePhotoUseCase: LikePhotoUseCase,
    private val unlikePhotoUseCase: UnlikePhotoUseCase,
    private val requestAccessTokenUseCase: RequestAccessTokenUseCase,
    private val setAccessTokenUseCase: SetAccessTokenUseCase
) : ViewModel() {
    private val _searchPhotosUiState = MutableStateFlow<SearchPhotosUiState>(SearchPhotosUiState.Loading)
    val searchPhotosUiState = _searchPhotosUiState.asStateFlow()

    private val _likePhotoUiState = MutableStateFlow<LikePhotoUiState>(LikePhotoUiState.Loading)
    val likePhotoUiState = _likePhotoUiState.asStateFlow()

    private val _unlikePhotoUiState = MutableStateFlow<UnlikePhotoUiState>(UnlikePhotoUiState.Loading)
    val unlikePhotoUiState = _unlikePhotoUiState.asStateFlow()

    private val _requestAccessTokenUiState = MutableStateFlow<RequestAccessTokenUiState>(RequestAccessTokenUiState.Loading)
    val requestAccessTokenUiState = _requestAccessTokenUiState.asStateFlow()

    private val _setAccessTokenUiState = MutableStateFlow<SetAccessTokenUiState>(SetAccessTokenUiState.Loading)
    val setAccessTokenUiState = _setAccessTokenUiState.asStateFlow()

    var photos = mutableStateListOf<UnsplashPhotoModel>()
        private set

    fun searchPhotos(query: String, page: Int) = viewModelScope.launch {
        if (query.isBlank()) {
            _searchPhotosUiState.value = SearchPhotosUiState.QueryEmpty
        } else {
            _searchPhotosUiState.value = SearchPhotosUiState.Loading
            searchPhotosUseCase(query, page)
                .onSuccess {
                    it.catch { remoteError ->
                        _searchPhotosUiState.value = SearchPhotosUiState.Error(remoteError)
                    }.collect { result ->
                        _searchPhotosUiState.value = SearchPhotosUiState.Success(result)
                    }
                }
                .onFailure {
                    _searchPhotosUiState.value = SearchPhotosUiState.Error(it)
                }
        }
    }

    fun likePhoto(id: String) = viewModelScope.launch {
        likePhotoUseCase(id)
            .onSuccess {
                it.catch { remoteError ->
                    _likePhotoUiState.value = LikePhotoUiState.Error(remoteError)
                }.collect { result ->
                    _likePhotoUiState.value = LikePhotoUiState.Success(result)
                }
            }
            .onFailure {
                _likePhotoUiState.value = LikePhotoUiState.Error(it)
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

    fun requestAccessToken(code: String) = viewModelScope.launch {
        requestAccessTokenUseCase(code)
            .onSuccess {
                it.catch { remoteError ->
                    _requestAccessTokenUiState.value = RequestAccessTokenUiState.Error(remoteError)
                }.collect { result ->
                    _requestAccessTokenUiState.value = RequestAccessTokenUiState.Success
                    setAccessToken(result.accessToken)
                }
            }
            .onFailure {
                _requestAccessTokenUiState.value = RequestAccessTokenUiState.Error(it)
            }
    }

    fun setAccessToken(accessToken: String) = viewModelScope.launch {
        setAccessTokenUseCase(accessToken)
            .onSuccess {
                _setAccessTokenUiState.value = SetAccessTokenUiState.Success
            }
            .onFailure {
                _setAccessTokenUiState.value = SetAccessTokenUiState.Error(it)
            }
    }

    fun addPhotos(list: List<UnsplashPhotoModel>) {
        photos.addAll(list)
    }

    fun clearPhotos() {
        photos.clear()
    }
}