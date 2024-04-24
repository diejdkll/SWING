package com.project.swing.data.remote.datasource

import com.project.swing.data.remote.dto.AccessTokenResponse
import com.project.swing.data.remote.dto.LikePhotoResponse
import com.project.swing.data.remote.dto.UnsplashPhoto
import com.project.swing.data.remote.dto.UnsplashResponse
import kotlinx.coroutines.flow.Flow

interface UnsplashDataSource {
    suspend fun searchPhotos(query: String, page: Int): Flow<UnsplashResponse>

    suspend fun getLikePhotos(page: Int): Flow<List<UnsplashPhoto>>

    suspend fun likePhotos(id: String): Flow<LikePhotoResponse>

    suspend fun unlikePhotos(id: String): Flow<LikePhotoResponse>

    suspend fun requestAccessToken(code: String): Flow<AccessTokenResponse>
}