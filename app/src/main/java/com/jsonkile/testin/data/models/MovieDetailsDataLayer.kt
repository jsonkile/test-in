package com.jsonkile.testin.data.models

import com.jsonkile.testin.ui.models.MovieDetailsUILayer

data class MovieDetailsDataLayer(
    val actors: String? = "",
    val director: String? = "",
    val imdbID: String? = "",
    val plot: String? = "",
    val poster: String? = "",
    val released: String? = "",
    val title: String? = "",
    val writer: String? = "",
    val imdbRating: String? = "",
    val genre: String? = "",
    val runtime: String? = "",
)

fun MovieDetailsDataLayer.toMovieDetailsUILayer() = MovieDetailsUILayer(
    imdbID = imdbID.orEmpty(),
    posterUrl = poster.orEmpty(),
    title = title.orEmpty(),
    plot = plot.orEmpty(),
    releaseDate = released.orEmpty(),
    cast = actors.orEmpty(),
    director = director.orEmpty(),
    writer = writer.orEmpty(),
    imdbRating = imdbRating.orEmpty(),
    genre = genre.orEmpty(),
    runtime = runtime.orEmpty()
)
