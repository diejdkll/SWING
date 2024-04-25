package com.project.network.datasource

import com.project.network.api.UnsplashAPI
import com.project.network.dto.AccessTokenResponse
import com.project.network.dto.LikePhotoResponse
import com.project.network.dto.UnsplashPhoto
import com.project.network.dto.UnsplashResponse
import com.project.network.util.RequestHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class UnsplashDataSourceImpl @Inject constructor(
    private val unsplashAPI: UnsplashAPI
) : UnsplashDataSource {
    override suspend fun searchPhotos(query: String, page: Int): Flow<UnsplashResponse> = flow {
        emit(
            RequestHandler<UnsplashResponse>()
                .httpRequest { unsplashAPI.searchPhotos(query = query, page = page) }
                .sendRequest()
        )
    }.flowOn(Dispatchers.IO)

    override suspend fun getLikePhotos(page: Int): Flow<List<UnsplashPhoto>> = flow {
        emit(
            RequestHandler<List<UnsplashPhoto>>()
                .httpRequest { unsplashAPI.getLikePhotos(page = page) }
                .sendRequest()
        )
    }.flowOn(Dispatchers.IO)

    override suspend fun likePhotos(id: String): Flow<LikePhotoResponse> = flow {
        emit(
            RequestHandler<LikePhotoResponse>()
                .httpRequest { unsplashAPI.likePhoto(id = id) }
                .sendRequest()
        )
    }.flowOn(Dispatchers.IO)

    override suspend fun unlikePhotos(id: String): Flow<LikePhotoResponse> = flow {
        emit(
            RequestHandler<LikePhotoResponse>()
                .httpRequest { unsplashAPI.unlikePhoto(id = id) }
                .sendRequest()
        )
    }.flowOn(Dispatchers.IO)

    override suspend fun requestAccessToken(code: String): Flow<AccessTokenResponse> = flow {
        emit(
            RequestHandler<AccessTokenResponse>()
                .httpRequest { unsplashAPI.requestAccessToken(code = code) }
                .sendRequest()
        )
    }.flowOn(Dispatchers.IO)
}