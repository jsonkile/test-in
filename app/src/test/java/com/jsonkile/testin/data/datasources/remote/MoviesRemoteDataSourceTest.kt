package com.jsonkile.testin.data.datasources.remote

import com.jsonkile.testin.data.models.MovieDataLayer
import com.jsonkile.testin.data.models.MovieDetailsDataLayer
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class MoviesRemoteDataSourceTest {

    private lateinit var moviesApi: MoviesApi
    private lateinit var moviesRemoteDataSource: MoviesRemoteDataSource

    @Before
    fun setup() {
        moviesApi = object : MoviesApi {
            override suspend fun searchMovies(keyword: String): List<MovieDataLayer> = listOf(
                MovieDataLayer("imdb", "poster", "title", "keyword"),
                MovieDataLayer("imdb", "poster", "title", "keyword")
            )

            override suspend fun getMovie(imdbID: String): MovieDetailsDataLayer =
                MovieDetailsDataLayer(
                    title = "title",
                    director = "director",
                    actors = "actors",
                    poster = "poster",
                    released = "released",
                    writer = "writer",
                    plot = "plot",
                    genre = "genre",
                    imdbRating = "imdbRating"
                )
        }

        moviesRemoteDataSource = MoviesRemoteDataSource(moviesApi)
    }

    @Test
    fun `test search movies`() {
        runBlocking {
            Assert.assertEquals(2, moviesRemoteDataSource.searchMovies("").size)
            Assert.assertEquals("imdb", moviesRemoteDataSource.searchMovies("").first().imdbID)
            Assert.assertEquals("poster", moviesRemoteDataSource.searchMovies("").first().poster)
            Assert.assertEquals("title", moviesRemoteDataSource.searchMovies("").first().title)
            Assert.assertEquals("keyword", moviesRemoteDataSource.searchMovies("").first().keyword)

            Assert.assertEquals("imdb", moviesRemoteDataSource.searchMovies("").last().imdbID)
            Assert.assertEquals("poster", moviesRemoteDataSource.searchMovies("").last().poster)
            Assert.assertEquals("title", moviesRemoteDataSource.searchMovies("").last().title)
            Assert.assertEquals("keyword", moviesRemoteDataSource.searchMovies("").last().keyword)
        }
    }

    @Test
    fun `test get movie`() {
        runBlocking {
            Assert.assertEquals("title", moviesRemoteDataSource.getMovie("").title)
            Assert.assertEquals("director", moviesRemoteDataSource.getMovie("").director)
            Assert.assertEquals("actors", moviesRemoteDataSource.getMovie("").actors)
            Assert.assertEquals("poster", moviesRemoteDataSource.getMovie("").poster)
            Assert.assertEquals("released", moviesRemoteDataSource.getMovie("").released)
            Assert.assertEquals("writer", moviesRemoteDataSource.getMovie("").writer)
            Assert.assertEquals("plot", moviesRemoteDataSource.getMovie("").plot)
            Assert.assertEquals("genre", moviesRemoteDataSource.getMovie("").genre)
            Assert.assertEquals("imdbRating", moviesRemoteDataSource.getMovie("").imdbRating)
        }
    }

    @Test
    fun `test that calling delete movies throws an error`() {
        Assert.assertThrows(NotImplementedError::class.java) {
            runBlocking { moviesRemoteDataSource.deleteMovies() }
        }
    }

    @Test
    fun `test that calling store movies throws an error`() {
        Assert.assertThrows(NotImplementedError::class.java) {
            runBlocking { moviesRemoteDataSource.storeMovies(listOf()) }
        }
    }
}