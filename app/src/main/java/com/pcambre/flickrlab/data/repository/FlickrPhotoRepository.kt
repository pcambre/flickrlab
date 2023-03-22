package com.pcambre.flickrlab.data.repository

import com.pcambre.flickrlab.data.model.SearchPhotoResponseDTO
import com.pcambre.flickrlab.data.service.*
import com.pcambre.flickrlab.util.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

const val SEARCH_PAGE_SIZE = "25"

interface FlickrPhotoRepository {
    fun search(query: String): Flow<OperationResult<SearchPhotoResponseDTO>>
}

class FlickrPhotoRepositoryImpl(private val remoteDataSource: FlickrRemoteDataSource): FlickrPhotoRepository {

    override fun search(query: String): Flow<OperationResult<SearchPhotoResponseDTO>> =
        remoteDataSource.search(query = query, perPage = SEARCH_PAGE_SIZE).processNetworkOperation()
            .flowOn(Dispatchers.IO)

    private fun <T : Any> Flow<NetworkResult<T>>.processNetworkOperation(): Flow<OperationResult<T>> =
        map { networkResult ->
            when (networkResult) {
                is NetworkError -> OperationError()
                is NetworkException -> OperationNetworkFailure()
                is NetworkSuccess -> OperationSuccess(networkResult.data)
            }
        }
}