package com.project.swing.domain.usecase

import com.project.swing.domain.repository.UnsplashRepository
import javax.inject.Inject

class SetAccessTokenUseCase @Inject constructor(
    private val unsplashRepository: UnsplashRepository
) {
    suspend operator fun invoke(accessToken: String) = runCatching {
        unsplashRepository.setAccessToken(accessToken)
    }
}