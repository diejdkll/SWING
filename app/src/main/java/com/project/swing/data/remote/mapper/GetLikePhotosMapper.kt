package com.project.swing.data.remote.mapper

import com.project.swing.data.remote.dto.GetLikePhotosResponse
import com.project.swing.data.remote.dto.LikePhotoResponse
import com.project.swing.domain.model.GetLikePhotosResponseModel
import com.project.swing.domain.model.LikePhotoResponseModel

fun GetLikePhotosResponse.toModel(): GetLikePhotosResponseModel =
    GetLikePhotosResponseModel(
        photo = this.photo.map { it.toModel() }
    )