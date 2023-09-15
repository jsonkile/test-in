package com.jsonkile.testin.ui.models

data class MovieDetailsUILayer(
    val imdbID: String = "",
    val posterUrl: String = "",
    val title: String = "",
    val plot: String = "",
    val releaseDate: String = "",
    val cast: String = "",
    val director: String = "",
    val writer: String? = "",
    val imdbRating: String? = "",
    val genre: String? = "",
    val runtime: String? = "",
)
