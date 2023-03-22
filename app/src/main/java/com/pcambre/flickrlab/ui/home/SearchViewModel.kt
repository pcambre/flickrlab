package com.pcambre.flickrlab.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pcambre.flickrlab.data.model.PhotoDTO
import com.pcambre.flickrlab.domain.usecase.SearchPhotosUseCase
import com.pcambre.flickrlab.util.doIfFailure
import com.pcambre.flickrlab.util.doIfSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

//Also we can have different fields, in this case searching can be other attribute.
sealed class SearchScreenUiState {
    object Idle : SearchScreenUiState()
    object Searching : SearchScreenUiState()
    object EmptySearchResult : SearchScreenUiState()
    object Error : SearchScreenUiState()

    //TODO: Map PhotoDTO into a UI object
    data class Success(val result: List<PhotoDTO>) : SearchScreenUiState()
}

class SearchViewModel(private val searchUseCase: SearchPhotosUseCase) : ViewModel() {

    private val _state = MutableStateFlow<SearchScreenUiState>(SearchScreenUiState.Idle)
    val state: StateFlow<SearchScreenUiState> get() = _state

    private val _query = MutableStateFlow("")
    val query = _query.asStateFlow()

    fun updateQuery(newQuery: String) {
        _query.value = newQuery
    }

    fun searchPhotos() {
        viewModelScope.launch(Dispatchers.IO) {
            _state.value = SearchScreenUiState.Searching
            searchUseCase.execute(query.value).collectLatest { result ->
                result.doIfSuccess { searchResponse ->
                    _state.value = if (searchResponse.photos.photo.isEmpty()) {
                        SearchScreenUiState.EmptySearchResult
                    } else {
                        SearchScreenUiState.Success(result = searchResponse.photos.photo)
                    }
                }.doIfFailure {
                    _state.value = SearchScreenUiState.Error
                }
            }
        }
    }
}