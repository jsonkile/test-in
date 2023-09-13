package com.jsonkile.testin.data.datasources.di

import android.content.Context
import androidx.room.Room
import com.jsonkile.testin.data.datasources.cache.AppDatabase
import com.jsonkile.testin.data.datasources.cache.MoviesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [DatabaseModule::class]
)
class FakeDatabaseModule {

    @Provides
    fun provideAppDatabase(@ApplicationContext applicationContext: Context): AppDatabase {
        return Room.inMemoryDatabaseBuilder(
            applicationContext, AppDatabase::class.java
        ).build()
    }

    @Provides
    fun provideMoviesDao(appDatabase: AppDatabase): MoviesDao {
        return appDatabase.moviesDao()
    }

}