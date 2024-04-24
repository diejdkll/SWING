package com.project.swing.domain.usecase

import com.project.swing.domain.repository.UnsplashRepository
import javax.inject.Inject

class GetLikePhotosUseCase @Inject constructor(
    private val unsplashRepository: UnsplashRepository
) {
    suspend operator fun invoke(page: Int) = runCatching {
        unsplashRepository.getLikePhotos(page)
    }
}