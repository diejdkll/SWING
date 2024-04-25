package com.project.domain.usecase

import com.project.data.repository.UnsplashRepository
import javax.inject.Inject

class RequestAccessTokenUseCase @Inject constructor(
    private val unsplashRepository: UnsplashRepository
) {
    suspend operator fun invoke(code: String) = runCatching {
        unsplashRepository.requestAccessToken(code = code)
    }
}