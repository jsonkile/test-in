package com.jsonkile.testin.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jsonkile.testin.ui.models.MovieUILayer

@Entity(tableName = "movies")
data class MovieDataLayer(
    @PrimaryKey(autoGenerate = false)
    val imdbID: String = "",
    val poster: String = "",
    val title: String = "",
    val keyword: String = ""
)


fun MovieDataLayer.toMovieUILayer() = MovieUILayer(title = title, imdbID = imdbID, posterUrl = poster)

fun List<MovieDataLayer>.toMovieUILayer(): List<MovieUILayer> = this.map { it.toMovieUILayer() }
