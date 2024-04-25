package com.project.network.mapper

import com.project.network.dto.LikePhotoResponse
import com.project.model.LikePhotoResponseModel

fun LikePhotoResponse.toModel(): LikePhotoResponseModel =
    LikePhotoResponseModel(
        photo = this.photo.toModel(),
        user = this.user.toModel()
    )