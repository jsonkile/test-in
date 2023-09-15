package com.jsonkile.testin.data.datasources

import com.jsonkile.testin.data.models.MovieDataLayer
import com.jsonkile.testin.data.models.MovieDetailsDataLayer

interface MoviesDataSource {
    suspend fun searchMovies(keyboard: String): List<MovieDataLayer>

    suspend fun getMovie(imdbID: String): MovieDetailsDataLayer {
        throw NotImplementedError("${this::class.java} should not call this method!")
    }

    suspend fun storeMovies(movies: List<MovieDataLayer>) {
        throw NotImplementedError("${this::class.java} should not call this method!")
    }

    suspend fun deleteMovies() {
        throw NotImplementedError("${this::class.java} should not call this method!")
    }
}