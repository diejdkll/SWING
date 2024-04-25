package com.project.network.mapper

import com.project.network.dto.UnsplashPhoto
import com.project.network.dto.UnsplashPhotoLinks
import com.project.network.dto.UnsplashPhotoUrls
import com.project.network.dto.UnsplashProfileImage
import com.project.network.dto.UnsplashResponse
import com.project.network.dto.UnsplashUser
import com.project.network.dto.UnsplashUserLinks
import com.project.model.UnsplashPhotoLinksModel
import com.project.model.UnsplashPhotoModel
import com.project.model.UnsplashPhotoUrlsModel
import com.project.model.UnsplashProfileImageModel
import com.project.model.UnsplashResponseModel
import com.project.model.UnsplashUserLinksModel
import com.project.model.UnsplashUserModel

fun UnsplashResponse.toModel(): UnsplashResponseModel =
    UnsplashResponseModel(
        total = this.total,
        totalPages = this.totalPages,
        results = this.results.map { it.toModel() }
    )

fun UnsplashPhoto.toModel(): UnsplashPhotoModel =
    UnsplashPhotoModel(
        id = this.id,
        createdAt = this.createdAt,
        width = this.width,
        height = this.height,
        color = this.color,
        blurHash = this.blurHash,
        likes = this.likes,
        likedByUser = this.likedByUser,
        description = this.description,
        user = this.user.toModel(),
        currentUserCollections = this.currentUserCollections,
        urls = this.urls.toModel(),
        links = this.links.toModel()
    )

fun UnsplashUser.toModel(): UnsplashUserModel =
    UnsplashUserModel(
        id = this.id,
        username = this.username,
        name = this.name,
        firstName = this.firstName,
        lastName = this.lastName,
        instagramUsername = this.instagramUsername,
        twitterUsername = this.twitterUsername,
        portfolioUrl = this.portfolioUrl,
        profileImage = this.profileImage.toModel(),
        links = this.links.toModel()
    )

fun UnsplashProfileImage.toModel(): UnsplashProfileImageModel =
    UnsplashProfileImageModel(
        small = this.small,
        medium = this.medium,
        large = this.large
    )

fun UnsplashUserLinks.toModel(): UnsplashUserLinksModel =
    UnsplashUserLinksModel(
        self = this.self,
        html = this.html,
        photos = this.photos,
        likes = this.likes
    )

fun UnsplashPhotoUrls.toModel(): UnsplashPhotoUrlsModel =
    UnsplashPhotoUrlsModel(
        raw = this.raw,
        full = this.full,
        regular = this.regular,
        small = this.small,
        thumb = this.thumb
    )

fun UnsplashPhotoLinks.toModel(): UnsplashPhotoLinksModel =
    UnsplashPhotoLinksModel(
        self = this.self,
        html = this.html,
        download = this.download
    )