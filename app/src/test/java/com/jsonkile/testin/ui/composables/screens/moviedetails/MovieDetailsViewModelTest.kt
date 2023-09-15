package com.jsonkile.testin.ui.composables.screens.moviedetails

import androidx.lifecycle.SavedStateHandle
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
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class MovieDetailsViewModelTest {

    private val dispatcher = UnconfinedTestDispatcher(TestCoroutineScheduler())

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
    }

    @After
    fun cleanUp() {
        Dispatchers.resetMain()
    }

    @Test
    fun `test saved state handle in view model receives correct imdb id`() = runTest {
        val movieDetailsViewModel = MovieDetailsViewModel(
            stateHandle = SavedStateHandle(mapOf("id" to "123")),
            moviesRepository = object : MoviesRepository {
                override suspend fun searchMovies(
                    keyword: String,
                    refresh: Boolean
                ): List<MovieDataLayer> {
                    return emptyList()
                }

                override suspend fun getMovie(imdbID: String): MovieDetailsDataLayer {
                    return MovieDetailsDataLayer()
                }
            })

        assertEquals("123", movieDetailsViewModel.imdbID)
    }

    @Test
    fun `test uiState receives movie from repo correctly`() = runTest {
        val movieDetailsViewModel = MovieDetailsViewModel(
            stateHandle = SavedStateHandle(mapOf("id" to "123")),
            moviesRepository = object : MoviesRepository {
                override suspend fun searchMovies(
                    keyword: String,
                    refresh: Boolean
                ): List<MovieDataLayer> = emptyList()

                override suspend fun getMovie(imdbID: String): MovieDetailsDataLayer =
                    MovieDetailsDataLayer(title = "title", genre = "genre")
            })

        assertEquals("genre", movieDetailsViewModel.movieDetailsUiState.movieDetailsUILayer?.genre)
        assertEquals("title", movieDetailsViewModel.movieDetailsUiState.movieDetailsUILayer?.title)
    }

    @Test
    fun `test uiState message is updated in case of an exception`() = runTest {
        val movieDetailsViewModel = MovieDetailsViewModel(
            stateHandle = SavedStateHandle(mapOf("id" to "123")),
            moviesRepository = object : MoviesRepository {
                override suspend fun searchMovies(
                    keyword: String,
                    refresh: Boolean
                ): List<MovieDataLayer> {
                    return emptyList()
                }

                override suspend fun getMovie(imdbID: String): MovieDetailsDataLayer {
                    throw NotFoundException()
                }
            })

        assertEquals(NotFoundException().message, movieDetailsViewModel.movieDetailsUiState.message)
    }
}