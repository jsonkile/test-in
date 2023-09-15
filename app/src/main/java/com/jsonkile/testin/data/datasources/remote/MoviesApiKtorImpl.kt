package com.jsonkile.testin.data.datasources.remote

import com.jsonkile.testin.data.models.MovieDataLayer
import com.jsonkile.testin.data.models.MovieDetailsApiResponse
import com.jsonkile.testin.data.models.MovieDetailsDataLayer
import com.jsonkile.testin.data.models.MoviesSearchApiResponse
import com.jsonkile.testin.data.models.toMovieDataLayer
import com.jsonkile.testin.data.models.toMovieDetailsDataLayer
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import javax.inject.Inject

class MoviesApiKtorImpl @Inject constructor(
    private val httpClient: HttpClient
) : MoviesApi {
    override suspend fun searchMovies(keyword: String): List<MovieDataLayer> =
        httpClient.get {
            parameter("s", keyword)
        }.body<MoviesSearchApiResponse>().toMovieDataLayer()

    override suspend fun getMovie(imdbID: String): MovieDetailsDataLayer =
        httpClient.get {
            parameter("i", imdbID)
        }.body<MovieDetailsApiResponse>().toMovieDetailsDataLayer()
}