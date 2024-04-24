package com.project.swing.data.remote.mapper

import com.project.swing.data.remote.dto.AccessTokenResponse
import com.project.swing.domain.model.AccessTokenResponseModel

fun AccessTokenResponse.toModel(): AccessTokenResponseModel =
    AccessTokenResponseModel(
        accessToken = this.accessToken,
        tokenType = this.tokenType,
        scope = this.scope,
        createdAt = this.createdAt
    )