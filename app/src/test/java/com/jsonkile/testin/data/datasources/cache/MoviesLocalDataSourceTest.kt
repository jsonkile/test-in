package com.jsonkile.testin.data.datasources.cache

import com.jsonkile.testin.data.models.MovieDataLayer
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class MoviesLocalDataSourceTest {

    private lateinit var moviesDao: MoviesDao
    private lateinit var dispatcher: CoroutineDispatcher
    private lateinit var moviesLocalDataSource: MoviesLocalDataSource
    private val testCoroutineScheduler: TestCoroutineScheduler = TestCoroutineScheduler()

    @Before
    fun setup() {
        dispatcher = StandardTestDispatcher(testCoroutineScheduler)
        moviesDao = object : MoviesDao {
            private val movies = mutableListOf(MovieDataLayer(imdbID = "imdbID", title = "title"))

            override fun getAll(keyword: String): List<MovieDataLayer> = movies

            override fun insertAll(vararg movies: MovieDataLayer) {
                this.movies.addAll(movies)
            }

            override fun deleteAll() {
                movies.clear()
            }
        }
        moviesLocalDataSource = MoviesLocalDataSource(moviesDao, dispatcher)
    }

    @Test
    fun `test search movies`() = runTest(dispatcher) {
        val result = moviesLocalDataSource.searchMovies("")
        advanceUntilIdle()
        assertEquals(1, result.size)
        assertEquals("imdbID", result.first().imdbID)
        assertEquals("title", result.first().title)
    }

    @Test
    fun `test store movies`() = runTest(dispatcher) {
        moviesLocalDataSource.storeMovies(listOf(MovieDataLayer(title = "Avengers")))
        val result = moviesLocalDataSource.searchMovies("")
        advanceUntilIdle()
        assertEquals(2, result.size)
        assertEquals("imdbID", result.first().imdbID)
        assertEquals("Avengers", result.last().title)
    }

    @Test
    fun `test delete movies`() = runTest(dispatcher) {
        moviesLocalDataSource.deleteMovies()
        val result = moviesLocalDataSource.searchMovies("")
        advanceUntilIdle()
        assertEquals(0, result.size)
    }

    @Test
    fun `test that calling get movie throws an error`() {
        assertThrows(NotImplementedError::class.java) {
            runBlocking { moviesLocalDataSource.getMovie("") }
        }
    }
}