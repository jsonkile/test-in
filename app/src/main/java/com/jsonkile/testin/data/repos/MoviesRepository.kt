package com.jsonkile.testin.data.repos

import com.jsonkile.testin.data.datasources.MoviesDataSource
import com.jsonkile.testin.data.datasources.di.MoviesLocalDataSource
import com.jsonkile.testin.data.datasources.di.MoviesRemoteDataSource
import com.jsonkile.testin.data.models.MovieItem
import com.jsonkile.testin.util.toMoviesList
import javax.inject.Inject

interface MoviesRepository {
    suspend fun searchMovies(keyword: String, refresh: Boolean): List<MovieItem>
}

class MoviesRepositoryImpl @Inject constructor(
    @MoviesRemoteDataSource private val moviesRemoteDataSource: MoviesDataSource,
    @MoviesLocalDataSource private val moviesLocalDataSource: MoviesDataSource
) :
    MoviesRepository {
    override suspend fun searchMovies(keyword: String, refresh: Boolean): List<MovieItem> {
        return if (refresh) {
            val localDataSource =
                (moviesLocalDataSource as com.jsonkile.testin.data.datasources.cache.MoviesLocalDataSource)

            localDataSource.deleteMovies()
            localDataSource.storeMovies(moviesRemoteDataSource.searchMovies(keyword))

            moviesLocalDataSource.searchMovies(keyword)
        } else {
            moviesLocalDataSource.searchMovies(keyword)
        }
    }

}