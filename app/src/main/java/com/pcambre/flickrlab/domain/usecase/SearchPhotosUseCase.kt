package com.pcambre.flickrlab.domain.usecase

import com.pcambre.flickrlab.data.model.SearchPhotoResponseDTO
import com.pcambre.flickrlab.data.repository.FlickrPhotoRepository
import com.pcambre.flickrlab.util.OperationResult
import kotlinx.coroutines.flow.Flow

class SearchPhotosUseCase(private val repository: FlickrPhotoRepository) :
    FlowUseCase<OperationResult<SearchPhotoResponseDTO>, String>() {

    override fun execute(params: String?): Flow<OperationResult<SearchPhotoResponseDTO>> =
        repository.search(params.orEmpty())
}