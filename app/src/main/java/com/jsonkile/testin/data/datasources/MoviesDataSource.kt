package com.jsonkile.testin.data.datasources

import com.jsonkile.testin.data.models.MovieItem

interface MoviesDataSource {
    suspend fun searchMovies(keyboard: String): List<MovieItem>
}