package com.jsonkile.testin.data.datasources.cache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.jsonkile.testin.data.models.MovieItem

@Dao
interface MoviesDao {
    @Query("SELECT * FROM movies WHERE keyword = :keyword")
    fun getAll(keyword: String): List<MovieItem>

    @Insert
    fun insertAll(vararg movies: MovieItem)

    @Query("Delete from movies")
    fun deleteAll()
}