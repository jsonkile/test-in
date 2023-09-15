package com.jsonkile.testin.data.models

import com.jsonkile.testin.ui.models.MovieUILayer
import org.junit.Assert
import org.junit.Test

class MovieDataLayerKtTest {

    @Test
    fun test_movieDataLayer_toMovieUiLayer() {
        Assert.assertEquals(
            MovieUILayer(imdbID = "imdbID", posterUrl = "poster", title = "title"),
            MovieDataLayer(
                imdbID = "imdbID",
                poster = "poster",
                title = "title",
                keyword = "keyword"
            ).toMovieUILayer()
        )
    }

    @Test
    fun test_listOfMovieDataLayer_toListOfMovieUiLayer() {
        Assert.assertEquals(
            listOf(
                MovieUILayer(imdbID = "imdbID", posterUrl = "poster", title = "title"),
                MovieUILayer(imdbID = "imdbID2", posterUrl = "poster", title = "title")
            ),
            listOf(
                MovieDataLayer(
                    imdbID = "imdbID",
                    poster = "poster",
                    title = "title",
                    keyword = "keyword"
                ),
                MovieDataLayer(
                    imdbID = "imdbID2",
                    poster = "poster",
                    title = "title",
                    keyword = "keyword"
                )
            ).toMovieUILayer()
        )
    }
}