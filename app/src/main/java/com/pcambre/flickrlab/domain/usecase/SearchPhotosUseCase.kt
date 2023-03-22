package com.pcambre.flickrlab.domain.usecase

import androidx.paging.PagingData
import com.pcambre.flickrlab.data.model.PhotoDTO
import com.pcambre.flickrlab.data.model.SearchPhotoResponseDTO
import com.pcambre.flickrlab.data.repository.FlickrPhotoRepository
import com.pcambre.flickrlab.util.OperationResult
import kotlinx.coroutines.flow.Flow

class SearchPhotosUseCase(private val repository: FlickrPhotoRepository) :
    FlowUseCase<PagingData<PhotoDTO>, String>() {

    override fun execute(params: String?): Flow<PagingData<PhotoDTO>> =
        repository.searchWithPaging(query = params.orEmpty())
}