package com.jsonkile.testin.data.models

import org.junit.Assert
import org.junit.Test

class MoviesSearchApiResponseKtTest {

    @Test
    fun test_movieSearchApiResponse_toListOfMovieDataLayer() {

        Assert.assertEquals(
            listOf(
                MovieDataLayer(
                    keyword = "keyword",
                    imdbID = "imdbID",
                    poster = "poster",
                    title = "title"
                ),
                MovieDataLayer(
                    keyword = "keyword",
                    imdbID = "imdbID2",
                    poster = "poster",
                    title = "title"
                )
            ),
            MoviesSearchApiResponse(
                search = listOf(
                    MoviesSearchApiResponse.Search(
                        imdbID = "imdbID",
                        poster = "poster",
                        title = "title"
                    ),
                    MoviesSearchApiResponse.Search(
                        imdbID = "imdbID2",
                        poster = "poster",
                        title = "title"
                    )
                )
            ).toMovieDataLayer(keyword = "keyword")
        )
    }
}