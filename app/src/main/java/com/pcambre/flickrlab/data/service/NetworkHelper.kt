package com.pcambre.flickrlab.data.service

import retrofit2.HttpException
import retrofit2.Response

suspend fun <T : Any> handleApiResponse(
    execute: suspend () -> Response<T>
): NetworkResult<T> {
    return try {
        val response = execute()
        val body = response.body()
        if (response.isSuccessful && body != null) {
            NetworkSuccess(body)
        } else {
            NetworkError(code = response.code(), message = response.message())
        }
    } catch (e: HttpException) {
        NetworkError(code = e.code(), message = e.message())
    } catch (e: Throwable) {
        NetworkException(e)
    }
}