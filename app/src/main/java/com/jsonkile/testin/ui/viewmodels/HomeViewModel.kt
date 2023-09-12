package com.jsonkile.testin.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jsonkile.testin.ui.models.Movie
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    data class HomeUiState(
        val movies: List<Movie> = emptyList(),
        val errorMessage: String? = null,
        val isFetchingMovies: Boolean = false,
        val searchKeyword: String = ""
    )

    var homeUiState by mutableStateOf(HomeUiState())
        private set

    fun search(keyword: String) {
        viewModelScope.launch {

        }
    }
}