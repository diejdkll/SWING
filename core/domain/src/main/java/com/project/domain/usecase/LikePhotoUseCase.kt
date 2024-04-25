package com.project.domain.usecase

import com.project.data.repository.UnsplashRepository
import javax.inject.Inject

class LikePhotoUseCase @Inject constructor(
    private val unsplashRepository: UnsplashRepository
) {
    suspend operator fun invoke(id: String) = runCatching {
        unsplashRepository.likePhotos(id)
    }
}