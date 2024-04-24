package com.project.swing.data.remote.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GetLikePhotosResponse(
    val photo: List<UnsplashPhoto>
)