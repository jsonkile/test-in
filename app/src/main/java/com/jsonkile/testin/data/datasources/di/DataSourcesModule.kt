package com.jsonkile.testin.data.datasources.di

import com.jsonkile.testin.data.datasources.MoviesDataSource
import com.jsonkile.testin.data.datasources.remote.MoviesApi
import com.jsonkile.testin.data.datasources.remote.MoviesApiKtorImpl
import com.jsonkile.testin.data.repos.MoviesRepository
import com.jsonkile.testin.data.repos.MoviesRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourcesModule {

    @Singleton
    @Binds
    abstract fun bindMoviesApi(moviesApiKtorImpl: MoviesApiKtorImpl): MoviesApi

    @MoviesRemoteDataSource
    @Singleton
    @Binds
    abstract fun bindMoviesRemoteDataSource(moviesRemoteDataSource: com.jsonkile.testin.data.datasources.remote.MoviesRemoteDataSource): MoviesDataSource

    @MoviesLocalDataSource
    @Singleton
    @Binds
    abstract fun bindMoviesLocalDataSource(moviesLocalDataSource: com.jsonkile.testin.data.datasources.cache.MoviesLocalDataSource): MoviesDataSource
}


