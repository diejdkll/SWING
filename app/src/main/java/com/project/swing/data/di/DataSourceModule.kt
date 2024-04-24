package com.project.swing.data.di

import com.project.swing.data.remote.datasource.UnsplashDataSource
import com.project.swing.data.remote.datasource.UnsplashDataSourceImpl
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