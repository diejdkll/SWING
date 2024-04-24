package com.project.swing.domain.model

data class AccessTokenResponseModel(
    val accessToken: String,
    val tokenType: String,
    val scope: String,
    val createdAt: Long
)