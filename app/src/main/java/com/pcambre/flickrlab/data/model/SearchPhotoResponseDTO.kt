package com.pcambre.flickrlab.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SearchPhotoResponseDTO(val photos: PhotosResponseDTO)

@JsonClass(generateAdapter = true)
data class PhotosResponseDTO(val page: Int, val pages: Int, val photo: List<PhotoDTO>)