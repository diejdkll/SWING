package com.project.domain.usecase

import com.project.data.repository.UnsplashRepository
import javax.inject.Inject

class GetAccessTokenUseCase @Inject constructor(
    private val unsplashRepository: UnsplashRepository
) {
    operator fun invoke() = runCatching {
        unsplashRepository.getAccessToken()
    }
}