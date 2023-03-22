package com.pcambre.flickrlab.ui.home

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.pcambre.flickrlab.data.model.PhotoDTO
import com.pcambre.flickrlab.data.service.*

class SearchResultPagingSource(
    private val query: String,
    private val pageSize: Int,
    private val api: FlickrApi,
) : PagingSource<Int, PhotoDTO>() {

    override fun getRefreshKey(state: PagingState<Int, PhotoDTO>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PhotoDTO> {

        val page = params.key ?: 1

        val response =
            handleApiResponse { api.search(query = query, perPage = pageSize, page = page) }
        return when (response) {
            is NetworkError -> {
                LoadResult.Error(Exception(response.message))
            }
            is NetworkException -> {
                LoadResult.Error(response.e)
            }
            is NetworkSuccess -> {
                LoadResult.Page(
                    data = response.data.photos.photo,
                    prevKey = if (page == 1) {
                        null
                    } else {
                        page.minus(1)
                    },
                    nextKey = if (response.data.photos.photo.isEmpty()) {
                        null
                    } else {
                        page.plus(1)
                    },
                )
            }
        }
    }

}