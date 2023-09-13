package com.jsonkile.testin.ui.composables.screens.moviedetails

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.jsonkile.testin.ui.models.MovieDetails

class MovieDetailsViewModel : ViewModel() {

    data class MovieDetailsUiState(
        val movieDetails: MovieDetails? = null,
        val message: String? = null,
        val isFetchingDetails: Boolean = false
    )

    var movieDetailsUiState by mutableStateOf(MovieDetailsUiState())
        private set

}