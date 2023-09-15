package com.jsonkile.testin.data.datasources.remote.di

import com.jsonkile.testin.data.datasources.remote.MoviesApi
import com.jsonkile.testin.data.datasources.remote.MoviesApiKtorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ApiModule {

    @Singleton
    @Binds
    abstract fun bindMoviesApi(moviesApiKtorImpl: MoviesApiKtorImpl): MoviesApi

}