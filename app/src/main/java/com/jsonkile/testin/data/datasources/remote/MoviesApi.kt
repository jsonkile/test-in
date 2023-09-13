package com.jsonkile.testin.data.datasources.remote

import com.jsonkile.testin.data.models.MovieItem
import com.jsonkile.testin.data.models.MoviesApiResponse
import com.jsonkile.testin.util.toMoviesList
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import javax.inject.Inject

interface MoviesApi {
    suspend fun searchMovies(keyword: String): List<MovieItem>
}

class MoviesApiKtorImpl @Inject constructor(
    private val httpClient: HttpClient
) : MoviesApi {
    override suspend fun searchMovies(keyword: String): List<MovieItem> =
        httpClient.get("http://www.omdbapi.com").body<MoviesApiResponse>().toMoviesList()
}