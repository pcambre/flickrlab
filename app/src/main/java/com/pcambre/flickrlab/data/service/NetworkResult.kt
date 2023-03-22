package com.pcambre.flickrlab.data.service

sealed interface NetworkResult<T : Any>

class NetworkSuccess<T : Any>(val data: T) : NetworkResult<T>
class NetworkError<T : Any>(val code: Int, val message: String?) : NetworkResult<T>
class NetworkException<T : Any>(val e: Throwable) : NetworkResult<T>

suspend fun <T : Any> NetworkResult<T>.onSuccess(
    executable: suspend (T) -> Unit
): NetworkResult<T> = apply {
    if (this is NetworkSuccess<T>) {
        executable(data)
    }
}