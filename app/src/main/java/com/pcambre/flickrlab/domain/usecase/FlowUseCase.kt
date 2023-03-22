package com.pcambre.flickrlab.domain.usecase

import kotlinx.coroutines.flow.Flow

abstract class FlowUseCase<T, in Params> {
    abstract fun execute(params: Params? = null): Flow<T>
}