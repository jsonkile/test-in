package com.jsonkile.testin.data.datasources.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class MoviesLocalDataSource

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class MoviesRemoteDataSource