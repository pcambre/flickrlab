package com.pcambre.flickrlab.util

sealed interface OperationResult<T : Any>

class OperationSuccess<T : Any>(val data: T) : OperationResult<T>
class OperationError<T : Any>() : OperationResult<T>
class OperationNetworkFailure<T : Any>() : OperationResult<T>

inline fun <reified T : Any> OperationResult<T>.doIfFailure(callback: () -> Unit): OperationResult<T> {
    if (this is OperationError || this is OperationNetworkFailure) {
        callback()
    }
    return this
}

inline fun <reified T : Any> OperationResult<T>.doIfSuccess(callback: (value: T) -> Unit): OperationResult<T> {
    if (this is OperationSuccess) {
        callback(data)
    }
    return this
}