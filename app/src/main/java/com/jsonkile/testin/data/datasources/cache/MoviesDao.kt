package com.jsonkile.testin.data.datasources.cache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.jsonkile.testin.data.models.MovieDataLayer

@Dao
interface MoviesDao {
    @Query("SELECT * FROM movies")
    fun getAll(): List<MovieDataLayer>

    @Insert
    fun insertAll(vararg movies: MovieDataLayer)

    @Query("Delete from movies")
    fun deleteAll()
}