package com.project.swing.domain.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GetLikePhotosResponseModel(
    val photo: List<UnsplashPhotoModel>
)