package com.jsonkile.testin.data.datasources.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jsonkile.testin.data.models.MovieDataLayer

@Database(entities = [MovieDataLayer::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun moviesDao(): MoviesDao
}