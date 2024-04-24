package com.project.swing.data.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UnsplashResponse(
    val total: Int,
    @Json(name = "total_pages") val totalPages: Int,
    val results: List<UnsplashPhoto>
)

@JsonClass(generateAdapter = true)
data class UnsplashPhoto(
    val id: String,
    @Json(name = "created_at") val createdAt: String,
    val width: Int,
    val height: Int,
    val color: String,
    @Json(name = "blur_hash") val blurHash: String?,
    val likes: Int,
    @Json(name = "liked_by_user") val likedByUser: Boolean,
    val description: String?,
    val user: UnsplashUser,
    @Json(name = "current_user_collections") val currentUserCollections: List<Any>,
    val urls: UnsplashPhotoUrls,
    val links: UnsplashPhotoLinks
)

@JsonClass(generateAdapter = true)
data class UnsplashUser(
    val id: String,
    val username: String,
    val name: String,
    @Json(name = "first_name") val firstName: String,
    @Json(name = "last_name") val lastName: String?,
    @Json(name = "instagram_username") val instagramUsername: String?,
    @Json(name = "twitter_username") val twitterUsername: String?,
    @Json(name = "portfolio_url") val portfolioUrl: String?,
    @Json(name = "profile_image") val profileImage: UnsplashProfileImage,
    val links: UnsplashUserLinks
)

@JsonClass(generateAdapter = true)
data class UnsplashProfileImage(
    val small: String,
    val medium: String,
    val large: String
)

@JsonClass(generateAdapter = true)
data class UnsplashUserLinks(
    val self: String,
    val html: String,
    val photos: String,
    val likes: String
)

@JsonClass(generateAdapter = true)
data class UnsplashPhotoUrls(
    val raw: String,
    val full: String,
    val regular: String,
    val small: String,
    val thumb: String
)

@JsonClass(generateAdapter = true)
data class UnsplashPhotoLinks(
    val self: String,
    val html: String,
    val download: String
)