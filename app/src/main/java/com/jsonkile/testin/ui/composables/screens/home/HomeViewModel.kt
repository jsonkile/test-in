package com.jsonkile.testin.ui.composables.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jsonkile.testin.data.models.toMovieUILayer
import com.jsonkile.testin.data.repos.MoviesRepository
import com.jsonkile.testin.ui.models.MovieUILayer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val moviesRepository: MoviesRepository) :
    ViewModel() {

    data class HomeUiState(
        val movies: List<MovieUILayer> = emptyList(),
        val message: String? = null,
        val isFetchingMovies: Boolean = false,
        val searchKeyword: String = ""
    )

    var homeUiState by mutableStateOf(HomeUiState())
        private set

    init {
        searchMovies(false)
    }

    fun searchMovies(refresh: Boolean = false) {
        viewModelScope.launch {
            try {
                homeUiState = homeUiState.copy(isFetchingMovies = true, movies = emptyList())
                val result = moviesRepository.searchMovies(
                    keyword = homeUiState.searchKeyword,
                    refresh = refresh
                )
                homeUiState = homeUiState.copy(movies = result.toMovieUILayer())
            } catch (e: Exception) {
                homeUiState = homeUiState.copy(message = e.message)
            } finally {
                homeUiState = homeUiState.copy(isFetchingMovies = false)
            }
        }
    }

    fun updateUiStateSearchKeyword(keyword: String) {
        homeUiState = homeUiState.copy(searchKeyword = keyword)
    }

    fun clearMessage() {
        homeUiState = homeUiState.copy(message = null)
    }
}