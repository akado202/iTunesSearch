package com.akado.itunessearch.di

import com.akado.itunessearch.data.local.TrackLocal
import com.akado.itunessearch.local.db.LocalDatabase
import com.akado.itunessearch.local.source.TrackLocalDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalSourceModule {

    @Provides
    @Singleton
    fun provideITunesDataSource(localDatabase: LocalDatabase): TrackLocal {
        return TrackLocalDataSource(localDatabase)
    }
}