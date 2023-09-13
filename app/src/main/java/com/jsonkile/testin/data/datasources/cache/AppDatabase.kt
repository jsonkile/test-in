package com.jsonkile.testin.data.datasources.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jsonkile.testin.data.models.MovieItem

@Database(entities = [MovieItem::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun moviesDao(): MoviesDao
}