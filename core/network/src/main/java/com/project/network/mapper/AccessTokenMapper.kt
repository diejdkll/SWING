package com.project.network.mapper

import com.project.network.dto.AccessTokenResponse
import com.project.model.AccessTokenResponseModel

fun AccessTokenResponse.toModel(): AccessTokenResponseModel =
    AccessTokenResponseModel(
        accessToken = this.accessToken,
        tokenType = this.tokenType,
        scope = this.scope,
        createdAt = this.createdAt
    )