package com.pcambre.flickrlab.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.pcambre.flickrlab.data.model.PhotoDTO
import com.pcambre.flickrlab.ui.common.component.ErrorView
import com.pcambre.flickrlab.ui.common.component.SearchTextView
import com.pcambre.flickrlab.ui.common.theme.FlickrLabTheme
import org.koin.androidx.compose.getViewModel

@Composable
fun SearchScreen(
    viewModel: SearchViewModel = getViewModel(),
    showLoading: (show: Boolean) -> Unit,
    onPhotoSelected: (photo: PhotoDTO) -> Unit
) {

    val state by viewModel.state.collectAsState()
    val query by viewModel.query.collectAsState()

    Scaffold(topBar = {
        SearchTextView(
            modifier = Modifier.padding(
                start = FlickrLabTheme.spaces.ml,
                end = FlickrLabTheme.spaces.ml,
                top = FlickrLabTheme.spaces.m
            ),
            query = query,
            onQueryChange = { viewModel.updateQuery(it) },
            onSearch = { viewModel.searchPhotos() }
        )
    }) { paddingValues ->
        Box(Modifier.padding(paddingValues)) {
            when (state) {
                SearchScreenUiState.EmptySearchResult -> {
                    showLoading(false)
                }
                SearchScreenUiState.Error -> {
                    showLoading(false)
                    ErrorView {
                        viewModel.searchPhotos()
                    }
                }
                SearchScreenUiState.Idle -> {

                }
                SearchScreenUiState.Searching -> {
                    showLoading(true)
                }
                is SearchScreenUiState.Success -> {
                    //Not a nice cast :(
                    (state as? SearchScreenUiState.Success)?.apply {
                        PhotoList(photos = this.result) {
                            onPhotoSelected(it)
                        }
                    }
                    showLoading(false)
                }
            }
        }
    }
}

@Composable
private fun PhotoList(photos: List<PhotoDTO>, onPhotoSelected: (photo: PhotoDTO) -> Unit) {
    LazyColumn(
        modifier = Modifier.padding(horizontal = FlickrLabTheme.spaces.ml),
        verticalArrangement = Arrangement.spacedBy(FlickrLabTheme.spaces.m)
    ) {
        item {
            Spacer(modifier = Modifier.height(FlickrLabTheme.spaces.m))
        }
        items(items = photos) { photo ->
            PhotoRow(
                modifier = Modifier,
                title = photo.title,
                thumbnailUrl = photo.thumbnailUrl.orEmpty()
            ) {
                onPhotoSelected(photo)
            }
        }
    }
}