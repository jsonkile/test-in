package com.jsonkile.testin.ui.composables.screens.home

import com.jsonkile.testin.data.datasources.remote.NotFoundException
import com.jsonkile.testin.data.models.MovieDataLayer
import com.jsonkile.testin.data.models.MovieDetailsDataLayer
import com.jsonkile.testin.data.repos.MoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {

    @Before
    fun setup() {
        Dispatchers.setMain(UnconfinedTestDispatcher(TestCoroutineScheduler()))
    }

    @After
    fun cleanUp() {
        Dispatchers.resetMain()
    }

    @Test
    fun `test search movies return movies on success`() = runTest {
        val moviesRepository = object : MoviesRepository {
            override suspend fun searchMovies(
                keyword: String,
                refresh: Boolean
            ): List<MovieDataLayer> {
                return listOf(MovieDataLayer(), MovieDataLayer())
            }

            override suspend fun getMovie(imdbID: String): MovieDetailsDataLayer {
                return MovieDetailsDataLayer(title = "title")
            }

        }

        val homeViewModel = HomeViewModel(moviesRepository = moviesRepository)

        homeViewModel.searchMovies()

        assertEquals(2, homeViewModel.homeUiState.movies.size)
    }

    @Test
    fun `test search movies throws error on exception`() = runTest {
        val moviesRepository = object : MoviesRepository {
            override suspend fun searchMovies(
                keyword: String,
                refresh: Boolean
            ): List<MovieDataLayer> {
                throw NotFoundException()
            }

            override suspend fun getMovie(imdbID: String): MovieDetailsDataLayer {
                return MovieDetailsDataLayer(title = "title")
            }

        }

        val homeViewModel = HomeViewModel(moviesRepository = moviesRepository)

        homeViewModel.searchMovies()

        assertEquals(NotFoundException().message, homeViewModel.homeUiState.message)
    }

    @Test
    fun `test update search keyword state`() {
        val moviesRepository = object : MoviesRepository {
            override suspend fun searchMovies(
                keyword: String,
                refresh: Boolean
            ): List<MovieDataLayer> {
                throw NotFoundException()
            }

            override suspend fun getMovie(imdbID: String): MovieDetailsDataLayer {
                return MovieDetailsDataLayer(title = "title")
            }

        }

        val homeViewModel = HomeViewModel(moviesRepository = moviesRepository)
        homeViewModel.updateUiStateSearchKeyword("search")
        assertEquals("search", homeViewModel.homeUiState.searchKeyword)
    }


    @Test
    fun `test clear message`() {
        val moviesRepository = object : MoviesRepository {
            override suspend fun searchMovies(
                keyword: String,
                refresh: Boolean
            ): List<MovieDataLayer> {
                throw NotFoundException()
            }

            override suspend fun getMovie(imdbID: String): MovieDetailsDataLayer {
                return MovieDetailsDataLayer(title = "title")
            }

        }

        val homeViewModel = HomeViewModel(moviesRepository = moviesRepository)
        homeViewModel.clearMessage()
        assertEquals(null, homeViewModel.homeUiState.message)
    }
}