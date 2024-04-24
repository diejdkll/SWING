package com.project.swing.domain.repository

import com.project.swing.domain.model.AccessTokenResponseModel
import com.project.swing.domain.model.LikePhotoResponseModel
import com.project.swing.domain.model.UnsplashPhotoModel
import com.project.swing.domain.model.UnsplashResponseModel
import kotlinx.coroutines.flow.Flow

interface UnsplashRepository {
    suspend fun searchPhotos(query: String, page: Int): Flow<UnsplashResponseModel>

    suspend fun getLikePhotos(page: Int): Flow<List<UnsplashPhotoModel>>

    suspend fun likePhotos(id: String): Flow<LikePhotoResponseModel>

    suspend fun unlikePhotos(id: String): Flow<LikePhotoResponseModel>

    suspend fun requestAccessToken(code: String): Flow<AccessTokenResponseModel>

    fun getAccessToken(): Flow<String>

    suspend fun setAccessToken(accessToken: String)
}