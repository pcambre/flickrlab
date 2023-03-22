package com.pcambre.flickrlab.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.pcambre.flickrlab.data.model.PhotoDTO
import com.pcambre.flickrlab.domain.usecase.SearchPhotosUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class SearchViewModel(private val searchUseCase: SearchPhotosUseCase) : ViewModel() {

    //TODO: We could create a UI model with query and isLoading
    private val _query = MutableStateFlow("")
    val query = _query.asStateFlow()

    //Necessary because searchResult starts always in refresh.Loading
    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _searchResult: MutableStateFlow<PagingData<PhotoDTO>> = MutableStateFlow(PagingData.empty())
    val searchResult: Flow<PagingData<PhotoDTO>> = _searchResult.asStateFlow()


    fun updateQuery(newQuery: String) {
        _query.value = newQuery
    }

    fun onLoadingFinished() {
        _isLoading.value = false
    }

    fun searchPhotos() {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.value = true
             searchUseCase.execute(query.value).cachedIn(viewModelScope).collect {
                 _searchResult.value = it
            }
        }
    }
}