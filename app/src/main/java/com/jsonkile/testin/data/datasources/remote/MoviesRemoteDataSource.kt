package com.jsonkile.testin.data.datasources.remote

import com.jsonkile.testin.data.datasources.MoviesDataSource
import com.jsonkile.testin.data.models.MovieItem
import javax.inject.Inject

class MoviesRemoteDataSource @Inject constructor(
    private val moviesApi: MoviesApi
) : MoviesDataSource {
    override suspend fun searchMovies(keyboard: String): List<MovieItem> =
        moviesApi.searchMovies(keyboard)

}