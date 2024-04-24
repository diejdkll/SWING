package com.project.swing.data.repository

import com.project.swing.data.local.AccessTokenDataSource
import com.project.swing.data.remote.datasource.UnsplashDataSource
import com.project.swing.data.remote.mapper.toModel
import com.project.swing.domain.model.AccessTokenResponseModel
import com.project.swing.domain.model.GetLikePhotosResponseModel
import com.project.swing.domain.model.LikePhotoResponseModel
import com.project.swing.domain.model.UnsplashPhotoModel
import com.project.swing.domain.model.UnsplashResponseModel
import com.project.swing.domain.repository.UnsplashRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UnsplashRepositoryImpl @Inject constructor(
    private val unsplashDataSource: UnsplashDataSource,
    private val accessTokenDataSource: AccessTokenDataSource
) : UnsplashRepository {
    override suspend fun searchPhotos(query: String, page: Int): Flow<UnsplashResponseModel> {
        return unsplashDataSource.searchPhotos(query, page).map { it.toModel() }
    }

    override suspend fun getLikePhotos(page: Int): Flow<List<UnsplashPhotoModel>> {
        return unsplashDataSource.getLikePhotos(page).map { photos -> photos.map { it.toModel() } }
    }

    override suspend fun likePhotos(id: String): Flow<LikePhotoResponseModel> {
        return unsplashDataSource.likePhotos(id).map { it.toModel() }
    }

    override suspend fun unlikePhotos(id: String): Flow<LikePhotoResponseModel> {
        return unsplashDataSource.unlikePhotos(id).map { it.toModel() }
    }

    override suspend fun requestAccessToken(code: String): Flow<AccessTokenResponseModel> {
        return unsplashDataSource.requestAccessToken(code).map { it.toModel() }
    }

    override fun getAccessToken(): Flow<String> {
        return accessTokenDataSource.getAccessToken()
    }

    override suspend fun setAccessToken(accessToken: String) {
        accessTokenDataSource.setAccessToken(accessToken)
    }
}