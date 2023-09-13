package com.jsonkile.testin.ui.composables.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jsonkile.testin.data.repos.MoviesRepository
import com.jsonkile.testin.ui.models.Movie
import com.jsonkile.testin.util.toMoviesList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val moviesRepository: MoviesRepository) :
    ViewModel() {

    data class HomeUiState(
        val movies: List<Movie> = emptyList(),
        val message: String? = null,
        val isFetchingMovies: Boolean = false,
        val searchKeyword: String = ""
    )

    var homeUiState by mutableStateOf(HomeUiState())
        private set

    fun search(keyword: String, refresh: Boolean = false) {
        viewModelScope.launch {
            val result = moviesRepository.searchMovies(keyword = keyword, refresh = refresh)
            updateUiStateMovies(result.toMoviesList())
        }
    }

    private fun updateUiStateMovies(movies: List<Movie>) {
        homeUiState = homeUiState.copy(movies = movies)
    }
}

val HomeViewModel.HomeUiState.showResultsSection: Boolean get() = !isFetchingMovies && movies.isNotEmpty()