package com.jsonkile.testin.data.datasources.cache

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.jsonkile.testin.data.models.MovieDataLayer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class MoviesRoomReadWriteDeleteTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var appDatabase: AppDatabase

    @Inject
    lateinit var moviesDao: MoviesDao

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun writeReadAndDeleteMovies() {
        moviesDao.insertAll(
            movies = arrayOf(
                MovieDataLayer(
                    imdbID = "123",
                    keyword = "sos",
                    poster = "poster",
                    title = "title"
                )
            )
        )

        val items = moviesDao.getAll(keyword = "sos")

        Assert.assertEquals(1, items.size)
        Assert.assertEquals("123", items.first().imdbID)
        Assert.assertEquals("title", items.first().title)
        Assert.assertEquals("poster", items.first().poster)

        moviesDao.deleteAll()

        Assert.assertEquals(emptyList<MovieDataLayer>(), moviesDao.getAll("sos"))
    }

}