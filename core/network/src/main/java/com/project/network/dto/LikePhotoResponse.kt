package com.project.network.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LikePhotoResponse(
    val photo: UnsplashPhoto,
    val user: UnsplashUser
)