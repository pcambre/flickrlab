package com.pcambre.flickrlab.data.service

import com.pcambre.flickrlab.data.model.SearchPhotoResponseDTO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FlickrRemoteDataSource(private val api: FlickrApi) {

    fun search(query: String, perPage: String): Flow<NetworkResult<SearchPhotoResponseDTO>> = flow {
        emit(handleApiResponse { api.search(query = query, perPage = perPage) })
    }

}