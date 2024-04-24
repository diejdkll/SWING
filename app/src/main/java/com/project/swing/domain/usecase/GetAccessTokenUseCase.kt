package com.project.swing.domain.usecase

import com.project.swing.domain.repository.UnsplashRepository
import javax.inject.Inject

class GetAccessTokenUseCase @Inject constructor(
    private val unsplashRepository: UnsplashRepository
) {
    operator fun invoke() = runCatching {
        unsplashRepository.getAccessToken()
    }
}