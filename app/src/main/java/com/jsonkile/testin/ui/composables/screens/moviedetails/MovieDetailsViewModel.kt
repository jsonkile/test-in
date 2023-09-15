package com.jsonkile.testin.ui.composables.screens.moviedetails

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jsonkile.testin.data.models.toMovieDetailsUILayer
import com.jsonkile.testin.data.models.toMovieUILayer
import com.jsonkile.testin.data.repos.MoviesRepository
import com.jsonkile.testin.ui.models.MovieDetailsUILayer
import com.jsonkile.testin.ui.models.MovieUILayer
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieDetailsViewModel @Inject constructor(
    stateHandle: SavedStateHandle,
    private val moviesRepository: MoviesRepository
) :
    ViewModel() {

    val imdbID = checkNotNull(stateHandle.get<String>("imdbID"))

    data class MovieDetailsUiState(
        val movieDetailsUILayer: MovieDetailsUILayer? = null,
        val message: String? = null,
        val isFetchingDetails: Boolean = false
    )

    var movieDetailsUiState by mutableStateOf(MovieDetailsUiState())
        private set

    init {
        fetchDetails()
    }

    fun fetchDetails() {
        viewModelScope.launch {
            try {
                movieDetailsUiState = movieDetailsUiState.copy(isFetchingDetails = true)
                val result = moviesRepository.getMovie(imdbID)
                movieDetailsUiState =
                    movieDetailsUiState.copy(movieDetailsUILayer = result.toMovieDetailsUILayer())
            } catch (e: Exception) {
                movieDetailsUiState = movieDetailsUiState.copy(message = e.message)
            } finally {
                movieDetailsUiState = movieDetailsUiState.copy(isFetchingDetails = false)
            }
        }
    }
}