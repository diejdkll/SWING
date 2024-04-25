package com.project.network.di

import com.project.network.datasource.UnsplashDataSource
import com.project.network.datasource.UnsplashDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {
    @Binds
    abstract fun bindUnsplashDataSource(
        unsplashDataSourceImpl: UnsplashDataSourceImpl
    ): UnsplashDataSource
}