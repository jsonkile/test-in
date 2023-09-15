package com.jsonkile.testin.data.datasources.remote

import com.jsonkile.testin.data.datasources.MoviesDataSource
import com.jsonkile.testin.data.models.MovieDataLayer
import com.jsonkile.testin.data.models.MovieDetailsDataLayer
import javax.inject.Inject

class MoviesRemoteDataSource @Inject constructor(
    private val moviesApi: MoviesApi
) : MoviesDataSource {
    override suspend fun searchMovies(keyboard: String): List<MovieDataLayer> =
        moviesApi.searchMovies(keyboard)

    override suspend fun getMovie(imdbID: String): MovieDetailsDataLayer =
        moviesApi.getMovie(imdbID)
}