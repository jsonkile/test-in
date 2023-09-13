package com.jsonkile.testin.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class MovieItem(
    @PrimaryKey(autoGenerate = false)
    val imdbID: String = "",
    val poster: String = "",
    val title: String = "",
    val keyword: String = ""
)
