package com.project.data.repository

import com.project.datastore.AccessTokenDataSource
import com.project.model.AccessTokenResponseModel
import com.project.model.LikePhotoResponseModel
import com.project.model.UnsplashPhotoModel
import com.project.model.UnsplashResponseModel
import com.project.network.datasource.UnsplashDataSource
import com.project.network.mapper.toModel
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