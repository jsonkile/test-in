package com.jsonkile.testin.data.datasources.di

import com.jsonkile.testin.data.datasources.MoviesDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourcesModule {

    @MoviesRemoteDataSource
    @Singleton
    @Binds
    abstract fun bindMoviesRemoteDataSource(moviesRemoteDataSource: com.jsonkile.testin.data.datasources.remote.MoviesRemoteDataSource): MoviesDataSource

    @MoviesLocalDataSource
    @Singleton
    @Binds
    abstract fun bindMoviesLocalDataSource(moviesLocalDataSource: com.jsonkile.testin.data.datasources.cache.MoviesLocalDataSource): MoviesDataSource
}


