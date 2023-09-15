package com.jsonkile.testin.data.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MoviesSearchApiResponse(
    @SerialName("Response")
    val response: String? = "",
    @SerialName("Search")
    val search: List<Search?>? = listOf(),
    @SerialName("totalResults")
    val totalResults: String? = ""
) {
    @Serializable
    data class Search(
        @SerialName("imdbID")
        val imdbID: String? = "",
        @SerialName("Poster")
        val poster: String? = "",
        @SerialName("Title")
        val title: String? = "",
    )
}


fun MoviesSearchApiResponse.toMovieDataLayer(keyword: String = ""): List<MovieDataLayer> =
    this.search.orEmpty().map {
        MovieDataLayer(
            imdbID = it?.imdbID.orEmpty(),
            poster = it?.poster.orEmpty(),
            title = it?.title.orEmpty(),
            keyword = keyword
        )
    }