package com.jsonkile.testin.data.datasources.cache

import com.jsonkile.testin.data.datasources.MoviesDataSource
import com.jsonkile.testin.data.datasources.di.IoDispatcher
import com.jsonkile.testin.data.models.MovieDataLayer
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MoviesLocalDataSource @Inject constructor(
    private val moviesDao: MoviesDao,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) :
    MoviesDataSource {
    override suspend fun searchMovies(keyboard: String): List<MovieDataLayer> =
        withContext(ioDispatcher) {
            moviesDao.getAll()
        }

    override suspend fun storeMovies(movies: List<MovieDataLayer>) {
        withContext(ioDispatcher) {
            moviesDao.insertAll(movies = movies.toTypedArray())
        }
    }

    override suspend fun deleteMovies() {
        withContext(ioDispatcher) {
            moviesDao.deleteAll()
        }
    }
}