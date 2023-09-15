package com.jsonkile.testin.data.datasources.remote

import com.jsonkile.testin.data.models.MovieDataLayer
import com.jsonkile.testin.data.models.MovieDetailsDataLayer

interface MoviesApi {
    suspend fun searchMovies(keyword: String): List<MovieDataLayer>

    suspend fun getMovie(imdbID: String): MovieDetailsDataLayer
}

