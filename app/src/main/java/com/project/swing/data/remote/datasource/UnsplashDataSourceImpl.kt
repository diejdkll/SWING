package com.project.swing.data.remote.datasource

import com.project.swing.data.remote.api.UnsplashAPI
import com.project.swing.data.remote.dto.AccessTokenResponse
import com.project.swing.data.remote.dto.GetLikePhotosResponse
import com.project.swing.data.remote.dto.LikePhotoResponse
import com.project.swing.data.remote.dto.UnsplashPhoto
import com.project.swing.data.remote.dto.UnsplashResponse
import com.project.swing.data.util.RequestHandler
import com.project.swing.domain.model.UnsplashPhotoModel
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