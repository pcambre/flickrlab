package com.pcambre.flickrlab.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.pcambre.flickrlab.data.model.PhotoDTO
import com.pcambre.flickrlab.ui.common.component.ErrorView
import com.pcambre.flickrlab.ui.common.component.SearchTextView
import com.pcambre.flickrlab.ui.common.theme.FlickrLabTheme
import org.koin.androidx.compose.getViewModel
import com.pcambre.flickrlab.R
import com.pcambre.flickrlab.ui.common.component.EmptyResultView

@Composable
fun SearchScreen(
    viewModel: SearchViewModel = getViewModel(),
    showLoading: (show: Boolean) -> Unit,
    onPhotoSelected: (photo: PhotoDTO) -> Unit,
) {

    val query by viewModel.query.collectAsState()
    val searchResult = viewModel.searchResult.collectAsLazyPagingItems()
    val showLoader by viewModel.isLoading.collectAsState()

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
            showLoading(showLoader)
            when {
                //Only during first loading
                searchResult.loadState.refresh is LoadState.Loading -> {
                    //noop
                }
                searchResult.loadState.refresh is LoadState.Error -> {
                    ErrorView {
                        viewModel.searchPhotos()
                    }
                }
                searchResult.itemCount > 0 -> {
                    viewModel.onLoadingFinished()
                    PhotoList(searchResult) {
                        onPhotoSelected(it)
                    }
                }
                searchResult.loadState.append.endOfPaginationReached && searchResult.itemCount == 0 -> {
                    viewModel.onLoadingFinished()
                    EmptyResultView(
                        title = stringResource(id = R.string.search_query_empty_title),
                        subtitle = stringResource(id = R.string.search_query_empty_subtitle))
                }
            }
        }
    }
}

@Composable
private fun PhotoList(
    photos: LazyPagingItems<PhotoDTO>,
    onPhotoSelected: (photo: PhotoDTO) -> Unit,
) {
    LazyColumn(
        modifier = Modifier.padding(horizontal = FlickrLabTheme.spaces.ml),
        verticalArrangement = Arrangement.spacedBy(FlickrLabTheme.spaces.m)
    ) {
        item {
            Spacer(modifier = Modifier.height(FlickrLabTheme.spaces.m))
        }
        //Unfortunatly flickr search seems that return duplicated items when paging
        items(items = photos, /*key = { it.id }*/) { photo ->
            PhotoRow(
                modifier = Modifier,
                title = photo?.title.orEmpty(),
                thumbnailUrl = photo?.thumbnailUrl.orEmpty()
            ) {
                photo?.apply {
                    onPhotoSelected(this)
                }
            }
        }
        when (photos.loadState.append) { // Pagination
            is LoadState.Error -> {
                item {
                    ErrorCell()
                }
            }
            is LoadState.Loading -> { // Pagination Loading UI
                item {
                    LoadingCell()
                }
            }
            else -> {}
        }
    }
}

@Composable
private fun LoadingCell() {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        CircularProgressIndicator(color = FlickrLabTheme.colors.primary)
    }
}

@Composable
private fun ErrorCell() {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(text = stringResource(id = R.string.search_query_error_cell))
    }
}