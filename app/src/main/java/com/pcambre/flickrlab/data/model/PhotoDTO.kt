package com.pcambre.flickrlab.data.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

//TODO: Map PhotoDTO into a UI entity
@Parcelize
@JsonClass(generateAdapter = true)
data class PhotoDTO(
    val id: String,
    @Json(name = "url_sq")
    val thumbnailUrl: String?,
    @Json(name = "url_o")
    val originalUrl: String?,
    val title: String
) : Parcelable