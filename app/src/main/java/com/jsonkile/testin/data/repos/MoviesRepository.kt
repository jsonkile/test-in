package com.jsonkile.testin.data.repos

import com.jsonkile.testin.data.models.MovieDataLayer
import com.jsonkile.testin.data.models.MovieDetailsDataLayer

interface MoviesRepository {
    suspend fun searchMovies(keyword: String, refresh: Boolean): List<MovieDataLayer>

    suspend fun getMovie(imdbID: String): MovieDetailsDataLayer
}