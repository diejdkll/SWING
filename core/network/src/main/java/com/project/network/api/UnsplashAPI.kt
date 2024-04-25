package com.project.network.api

import com.project.network.BuildConfig
import com.project.network.dto.AccessTokenResponse
import com.project.network.dto.LikePhotoResponse
import com.project.network.dto.UnsplashPhoto
import com.project.network.dto.UnsplashResponse
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface UnsplashAPI {
    @Headers("Accept-Version: v1")
    @GET("/search/photos")
    suspend fun searchPhotos(
        @Query("query") query: String,
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 30
    ): UnsplashResponse

    @Headers("Accept-Version: v1")
    @GET("/users/{username}/likes")
    suspend fun getLikePhotos(
        @Path("username") username: String = "hyeonseo0412",
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 30
    ): List<UnsplashPhoto>

    @Headers("Accept-Version: v1")
    @POST("/photos/{id}/like")
    suspend fun likePhoto(
        @Path("id") id: String
    ): LikePhotoResponse

    @Headers("Accept-Version: v1")
    @DELETE("/photos/{id}/like")
    suspend fun unlikePhoto(
        @Path("id") id: String
    ): LikePhotoResponse

    @POST
    suspend fun requestAccessToken(
        @Url url: String = "https://unsplash.com/oauth/token",
        @Query("client_id") clientId: String = BuildConfig.ACCESS_KEY,
        @Query("client_secret") clientSecret: String = BuildConfig.SECRET_KEY,
        @Query("redirect_uri") redirectUri: String = BuildConfig.REDIRECT_URL,
        @Query("code") code: String,
        @Query("grant_type") grantType: String = "authorization_code"
    ): AccessTokenResponse
}