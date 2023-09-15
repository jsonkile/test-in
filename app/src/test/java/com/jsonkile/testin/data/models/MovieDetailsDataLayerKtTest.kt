package com.jsonkile.testin.data.models

import com.jsonkile.testin.ui.models.MovieDetailsUILayer
import org.junit.Assert
import org.junit.Test

class MovieDetailsDataLayerKtTest {

    @Test
    fun test_movieDetailsDataLayer_toMovieDetailsUiLayer() {

        Assert.assertEquals(
            MovieDetailsUILayer(
                cast = "a",
                director = "b",
                imdbID = "c",
                plot = "d",
                posterUrl = "e",
                releaseDate = "f",
                title = "g",
                imdbRating = "h",
                writer = "i",
                genre = "j",
                runtime = "k"
            ),
            MovieDetailsDataLayer(
                actors = "a",
                director = "b",
                imdbID = "c",
                plot = "d",
                poster = "e",
                released = "f",
                title = "g",
                imdbRating = "h",
                writer = "i",
                genre = "j",
                runtime = "k"
            ).toMovieDetailsUILayer()
        )
    }
}