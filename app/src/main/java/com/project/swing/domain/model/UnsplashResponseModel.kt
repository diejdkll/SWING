package com.project.swing.domain.model

data class UnsplashResponseModel(
    val total: Int,
    val totalPages: Int,
    val results: List<UnsplashPhotoModel>
)

data class UnsplashPhotoModel(
    val id: String,
    val createdAt: String,
    val width: Int,
    val height: Int,
    val color: String,
    val blurHash: String?,
    val likes: Int,
    val likedByUser: Boolean,
    val description: String?,
    val user: UnsplashUserModel,
    val currentUserCollections: List<Any>,
    val urls: UnsplashPhotoUrlsModel,
    val links: UnsplashPhotoLinksModel
)

data class UnsplashUserModel(
    val id: String,
    val username: String,
    val name: String,
    val firstName: String,
    val lastName: String?,
    val instagramUsername: String?,
    val twitterUsername: String?,
    val portfolioUrl: String?,
    val profileImage: UnsplashProfileImageModel,
    val links: UnsplashUserLinksModel
)

data class UnsplashProfileImageModel(
    val small: String,
    val medium: String,
    val large: String
)

data class UnsplashUserLinksModel(
    val self: String,
    val html: String,
    val photos: String,
    val likes: String
)

data class UnsplashPhotoUrlsModel(
    val raw: String,
    val full: String,
    val regular: String,
    val small: String,
    val thumb: String
)

data class UnsplashPhotoLinksModel(
    val self: String,
    val html: String,
    val download: String
)
