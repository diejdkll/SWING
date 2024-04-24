package com.project.swing.data.di

import com.project.swing.data.repository.UnsplashRepositoryImpl
import com.project.swing.domain.repository.UnsplashRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindUnsplashRepository(
        unsplashRepositoryImpl: UnsplashRepositoryImpl
    ): UnsplashRepository
}