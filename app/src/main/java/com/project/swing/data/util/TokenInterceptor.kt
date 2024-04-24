package com.project.swing.data.util

import com.project.swing.data.local.AccessTokenDataSource
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class TokenInterceptor @Inject constructor(
    private val accessTokenDataSource: AccessTokenDataSource,
) : Interceptor {
    private val ignorePaths by lazy {
        listOf(
            "oauth"
        )
    }

    lateinit var accessToken: String

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val path = request.url.encodedPath

        runBlocking {
            accessToken = accessTokenDataSource.getAccessToken().first().replace("\"", "")
        }

        return chain.proceed(
            if (ignorePaths.contains(path)) {
                request
            } else {
                request.newBuilder()
                    .addHeader("Authorization", "Bearer $accessToken").build()
            }
        )
    }
}