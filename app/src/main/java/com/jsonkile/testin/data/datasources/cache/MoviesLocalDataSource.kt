package com.jsonkile.testin.data.datasources.cache

import com.jsonkile.testin.data.datasources.MoviesDataSource
import com.jsonkile.testin.data.datasources.di.IoDispatcher
import com.jsonkile.testin.data.models.MovieItem
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MoviesLocalDataSource @Inject constructor(
    private val moviesDao: MoviesDao,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) :
    MoviesDataSource {
    override suspend fun searchMovies(keyboard: String): List<MovieItem> =
        withContext(ioDispatcher) {
            moviesDao.getAll(keyboard)
        }

    suspend fun storeMovies(movies: List<MovieItem>) {
        withContext(ioDispatcher) {
            moviesDao.insertAll(movies = movies.toTypedArray())
        }
    }

    suspend fun deleteMovies() {
        withContext(ioDispatcher) {
            moviesDao.deleteAll()
        }
    }
}