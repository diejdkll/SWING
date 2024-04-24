package com.project.swing.data.remote.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LikePhotoResponse(
    val photo: UnsplashPhoto,
    val user: UnsplashUser
)