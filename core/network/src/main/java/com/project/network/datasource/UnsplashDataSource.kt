package com.project.network.datasource

import com.project.network.dto.AccessTokenResponse
import com.project.network.dto.LikePhotoResponse
import com.project.network.dto.UnsplashPhoto
import com.project.network.dto.UnsplashResponse
import kotlinx.coroutines.flow.Flow

interface UnsplashDataSource {
    suspend fun searchPhotos(query: String, page: Int): Flow<UnsplashResponse>

    suspend fun getLikePhotos(page: Int): Flow<List<UnsplashPhoto>>

    suspend fun likePhotos(id: String): Flow<LikePhotoResponse>

    suspend fun unlikePhotos(id: String): Flow<LikePhotoResponse>

    suspend fun requestAccessToken(code: String): Flow<AccessTokenResponse>
}