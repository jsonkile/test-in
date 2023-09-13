package com.jsonkile.testin.util

import com.jsonkile.testin.data.models.MovieItem
import com.jsonkile.testin.data.models.MoviesApiResponse
import com.jsonkile.testin.ui.composables.screens.home.HomeViewModel
import com.jsonkile.testin.ui.models.Movie

fun MovieItem.toMovie() =
    Movie(title = title, id = imdbID, posterUrl = poster)

fun List<MovieItem>.toMoviesList(): List<Movie> = this.map { it.toMovie() }

fun MoviesApiResponse.toMoviesList(): List<MovieItem> = this.search.orEmpty().map {
    MovieItem(
        imdbID = it?.imdbID.orEmpty(), poster = it?.poster.orEmpty(), title = it?.title.orEmpty()
    )
}