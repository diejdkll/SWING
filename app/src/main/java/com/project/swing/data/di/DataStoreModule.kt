package com.project.swing.data.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import com.project.swing.AccessToken
import com.project.swing.data.local.AccessTokenSerializer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {
    @Provides
    @Singleton
    fun provideAuthTokenDataStore(
        @ApplicationContext context: Context,
        authTokenSerializer: AccessTokenSerializer
    ): DataStore<AccessToken> =
        DataStoreFactory.create(
            serializer = authTokenSerializer,
        ) {
            context.dataStoreFile("accessToken.pb")
        }
}