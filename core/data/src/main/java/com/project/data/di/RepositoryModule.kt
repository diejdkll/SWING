package com.project.data.di

import com.project.data.repository.UnsplashRepositoryImpl
import com.project.data.repository.UnsplashRepository
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