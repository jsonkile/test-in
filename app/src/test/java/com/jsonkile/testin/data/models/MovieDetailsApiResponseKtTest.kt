package com.jsonkile.testin.data.models

import org.junit.Assert
import org.junit.Test

class MovieDetailsApiResponseKtTest {

    @Test
    fun test_movieDetailsApiResponse_toMovieDetailsDataLayer() {
        Assert.assertEquals(
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
            ),
            MovieDetailsApiResponse(
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
                runtime = "k",
                production = "production"
            ).toMovieDetailsDataLayer()
        )
    }
}