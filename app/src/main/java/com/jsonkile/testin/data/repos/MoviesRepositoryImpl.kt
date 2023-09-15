package com.jsonkile.testin.data.repos

import com.jsonkile.testin.data.datasources.MoviesDataSource
import com.jsonkile.testin.data.datasources.di.MoviesLocalDataSource
import com.jsonkile.testin.data.datasources.di.MoviesRemoteDataSource
import com.jsonkile.testin.data.models.MovieDataLayer
import com.jsonkile.testin.data.models.MovieDetailsDataLayer
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    @MoviesRemoteDataSource private val moviesRemoteDataSource: MoviesDataSource,
    @MoviesLocalDataSource private val moviesLocalDataSource: MoviesDataSource
) : MoviesRepository {
    override suspend fun searchMovies(keyword: String, refresh: Boolean): List<MovieDataLayer> {
        return if (refresh) {
            moviesLocalDataSource.deleteMovies()
            moviesLocalDataSource.storeMovies(moviesRemoteDataSource.searchMovies(keyword))

            moviesLocalDataSource.searchMovies(keyword)
        } else {
            moviesLocalDataSource.searchMovies(keyword)
        }
    }

    override suspend fun getMovie(imdbID: String): MovieDetailsDataLayer =
        moviesRemoteDataSource.getMovie(imdbID)
}