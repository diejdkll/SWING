package com.project.datastore

import androidx.datastore.core.DataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AccessTokenDataSource @Inject constructor(
    private val token: DataStore<AccessToken>
) {
    fun getAccessToken(): Flow<String> = token.data.map {
        it.accessToken ?: ""
    }

    suspend fun setAccessToken(accessToken: String) {
        token.updateData {
            it.toBuilder()
                .setAccessToken(accessToken)
                .build()
        }
    }

    suspend fun removeAccessToken() {
        token.updateData {
            it.toBuilder()
                .clearAccessToken()
                .build()
        }
    }
}