package com.project.domain.usecase

import com.project.data.repository.UnsplashRepository
import javax.inject.Inject

class GetLikePhotosUseCase @Inject constructor(
    private val unsplashRepository: UnsplashRepository
) {
    suspend operator fun invoke(page: Int) = runCatching {
        unsplashRepository.getLikePhotos(page)
    }
}