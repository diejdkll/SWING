package com.project.swing.domain.usecase

import com.project.swing.domain.repository.UnsplashRepository
import javax.inject.Inject

class UnlikePhotoUseCase @Inject constructor(
    private val unsplashRepository: UnsplashRepository
) {
    suspend operator fun invoke(id: String) = runCatching {
        unsplashRepository.unlikePhotos(id)
    }
}