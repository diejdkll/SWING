package com.project.swing.domain.usecase

import com.project.swing.domain.repository.UnsplashRepository
import javax.inject.Inject

class RequestAccessTokenUseCase @Inject constructor(
    private val unsplashRepository: UnsplashRepository
) {
    suspend operator fun invoke(code: String) = runCatching {
        unsplashRepository.requestAccessToken(code = code)
    }
}