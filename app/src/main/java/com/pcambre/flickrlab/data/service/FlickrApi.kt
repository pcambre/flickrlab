package com.pcambre.flickrlab.data.service

import com.pcambre.flickrlab.data.model.SearchPhotoResponseDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

private const val CONTENT_TYPE = "content_type"
private const val TEXT = "text"
private const val EXTRAS = "extras"
private const val PER_PAGE = "per_page"
private const val PAGE = "page"

//Possible extras url
// url_sq, url_t, url_s, url_q, url_m, url_n, url_z, url_c, url_l, url_o

interface FlickrApi {

    @GET("?method=flickr.photos.search")
    suspend fun search(@Query(TEXT) query: String,
                       //Search only pictures
                       @Query(CONTENT_TYPE) contentType: String = "1",
                       @Query(PER_PAGE) perPage: String,
                       @Query(PAGE) page: String = "1",
                       @Query(EXTRAS) extras: String = "url_sq,url_o",
    ): Response<SearchPhotoResponseDTO>
}