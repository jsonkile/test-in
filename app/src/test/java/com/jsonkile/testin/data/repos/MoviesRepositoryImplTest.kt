package com.jsonkile.testin.data.repos

import com.jsonkile.testin.data.datasources.MoviesDataSource
import com.jsonkile.testin.data.models.MovieDataLayer
import com.jsonkile.testin.data.models.MovieDetailsDataLayer
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class MoviesRepositoryImplTest {

    private lateinit var remoteDataSource: MoviesDataSource
    private lateinit var localDataSource: MoviesDataSource

    private lateinit var moviesRepository: MoviesRepositoryImpl

    @Before
    fun setup() {
        remoteDataSource = object : MoviesDataSource {
            private val movies = mutableListOf(
                MovieDataLayer(
                    imdbID = "imdbID",
                    title = "title",
                    poster = "poster",
                    keyword = "keyword"
                )
            )

            override suspend fun searchMovies(keyboard: String): List<MovieDataLayer> = movies

            override suspend fun getMovie(imdbID: String) = MovieDetailsDataLayer(title = "title")
        }

        localDataSource = object : MoviesDataSource {
            private val movies = mutableListOf<MovieDataLayer>()

            override suspend fun searchMovies(keyboard: String): List<MovieDataLayer> = movies

            override suspend fun storeMovies(movies: List<MovieDataLayer>) {
                this.movies.addAll(movies)
            }

            override suspend fun deleteMovies() {
                this.movies.clear()
            }
        }

        moviesRepository = MoviesRepositoryImpl(
            moviesRemoteDataSource = remoteDataSource,
            moviesLocalDataSource = localDataSource
        )
    }

    @Test
    fun `test get movie`() {
        runBlocking {
            Assert.assertEquals("title", moviesRepository.getMovie("").title)
        }
    }

    @Test
    fun `test search movies when refresh is false then true`() {
        runBlocking {
            Assert.assertEquals(0, moviesRepository.searchMovies("", refresh = false).size)
            Assert.assertEquals(1, moviesRepository.searchMovies("", refresh = true).size)
        }
    }

}