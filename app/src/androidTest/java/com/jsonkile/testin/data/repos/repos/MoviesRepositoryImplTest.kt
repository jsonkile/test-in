package com.jsonkile.testin.data.repos.repos

import com.jsonkile.testin.data.datasources.MoviesDataSource
import com.jsonkile.testin.data.datasources.di.MoviesLocalDataSource
import com.jsonkile.testin.data.datasources.di.MoviesRemoteDataSource
import com.jsonkile.testin.data.repos.MoviesRepositoryImpl
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltAndroidTest
class MoviesRepositoryImplTest {

    @get:Rule
    val hiltAndroidRule = HiltAndroidRule(this)

    @MoviesRemoteDataSource
    @Inject
    lateinit var moviesRemoteDataSource: MoviesDataSource

    @MoviesLocalDataSource
    @Inject
    lateinit var moviesLocalDataSource: MoviesDataSource

    @Before
    fun setup() {
        hiltAndroidRule.inject()
        Dispatchers.setMain(StandardTestDispatcher(TestCoroutineScheduler()))
    }

    @After
    fun cleanUp() {
        Dispatchers.resetMain()
    }

    @Test
    fun test_SearchMovies_ReturnsCorrectMovies() = runTest {
        val moviesRepository = MoviesRepositoryImpl(moviesRemoteDataSource, moviesLocalDataSource)

        Assert.assertEquals(
            true,
            moviesRepository.searchMovies(keyword = "happy", refresh = false).isEmpty()
        )

        Assert.assertEquals(
            10,
            moviesRepository.searchMovies(keyword = "happy", refresh = true).size
        )

        Assert.assertEquals(
            10,
            moviesRepository.searchMovies(keyword = "happy", refresh = false).size
        )

        Assert.assertEquals(
            0,
            moviesRepository.searchMovies(keyword = "sad", refresh = false).size
        )

        Assert.assertEquals(
            0,
            moviesRepository.searchMovies(keyword = "", refresh = false).size
        )
    }

    @Test
    fun test_GetMovie_ReturnsCorrectMovie() = runTest {
        val moviesRepository = MoviesRepositoryImpl(moviesRemoteDataSource, moviesLocalDataSource)

        Assert.assertEquals(
            "Happy Gilmore",
            moviesRepository.getMovie("").title
        )
    }
}