package com.project.domain.usecase

import com.project.data.repository.UnsplashRepository
import javax.inject.Inject

class SearchPhotosUseCase @Inject constructor(
    private val unsplashRepository: UnsplashRepository
) {
    suspend operator fun invoke(query: String, page: Int) = runCatching {
        unsplashRepository.searchPhotos(query, page)
    }
}