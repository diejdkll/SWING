package com.project.swing.data.remote.mapper

import com.project.swing.data.remote.dto.LikePhotoResponse
import com.project.swing.domain.model.LikePhotoResponseModel

fun LikePhotoResponse.toModel(): LikePhotoResponseModel =
    LikePhotoResponseModel(
        photo = this.photo.toModel(),
        user = this.user.toModel()
    )