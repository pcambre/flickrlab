package com.pcambre.flickrlab

import app.cash.turbine.test
import com.pcambre.flickrlab.data.model.SearchPhotoResponseDTO
import com.pcambre.flickrlab.domain.usecase.SearchPhotosUseCase
import com.pcambre.flickrlab.ui.home.SearchScreenUiState
import com.pcambre.flickrlab.ui.home.SearchViewModel
import com.pcambre.flickrlab.util.OperationSuccess
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class SearchViewModelTest {

    private val searchUseCase = mockk<SearchPhotosUseCase>(relaxed = true)
    private lateinit var viewModel: SearchViewModel

    @Before
    fun setUp() {
        viewModel = SearchViewModel(searchUseCase)
    }

    @Test
    fun `When user search success and the result is empty, then ui state is EmptySearch`() = runTest {
        val query = "Diego Forlan"
        coEvery { searchUseCase.execute(any()) } returns flow {
            emit(OperationSuccess<SearchPhotoResponseDTO>(
                mockk(relaxed = true)))
        }

        viewModel.updateQuery(query)
        viewModel.searchPhotos()
        //First state is Searching..
        viewModel.state.drop(1).test {
            awaitItem()
            assert(viewModel.state.value is SearchScreenUiState.EmptySearchResult)
            verify { searchUseCase.execute(any()) }
        }
    }
}