package com.project.swing.data.remote.mapper

import com.project.swing.data.remote.dto.UnsplashPhoto
import com.project.swing.data.remote.dto.UnsplashPhotoLinks
import com.project.swing.data.remote.dto.UnsplashPhotoUrls
import com.project.swing.data.remote.dto.UnsplashProfileImage
import com.project.swing.data.remote.dto.UnsplashResponse
import com.project.swing.data.remote.dto.UnsplashUser
import com.project.swing.data.remote.dto.UnsplashUserLinks
import com.project.swing.domain.model.UnsplashPhotoLinksModel
import com.project.swing.domain.model.UnsplashPhotoModel
import com.project.swing.domain.model.UnsplashPhotoUrlsModel
import com.project.swing.domain.model.UnsplashProfileImageModel
import com.project.swing.domain.model.UnsplashResponseModel
import com.project.swing.domain.model.UnsplashUserLinksModel
import com.project.swing.domain.model.UnsplashUserModel

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