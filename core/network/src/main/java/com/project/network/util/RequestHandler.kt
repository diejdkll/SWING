package com.project.network.util

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class RequestHandler<T> {
    private lateinit var httpRequest: suspend () -> T

    fun httpRequest(httpRequest: suspend () -> T) =
        this.apply { this.httpRequest = httpRequest }

    suspend fun sendRequest(): T =
        try {
            withContext(Dispatchers.IO) {
                httpRequest.invoke()
            }
        } catch (e: HttpException) {
            val message = e.message
            throw when (e.code()) {
                400 -> BadRequestException(message = message)
                401 -> UnauthorizedException(message = message)
                403 -> ForBiddenException(message = message)
                404 -> NotFoundException(message = message)
                in 500..503 -> ServerException(message = message)
                else -> OtherHttpException(
                    message = message,
                    code = e.code()
                )
            }
        } catch (e: Exception) {
            throw UnKnownException(message = e.message)
        }
}