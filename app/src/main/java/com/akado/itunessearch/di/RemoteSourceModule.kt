package com.akado.itunessearch.di

import com.akado.itunessearch.data.remote.ITunesRemote
import com.akado.itunessearch.remote.api.ApiService
import com.akado.itunessearch.remote.source.ITunesRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteSourceModule {

    @Provides
    @Singleton
    fun provideITunesDataSource(service: ApiService): ITunesRemote {
        return ITunesRemoteDataSource(service)
    }
}