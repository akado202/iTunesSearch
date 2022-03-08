package com.akado.itunessearch.di

import com.akado.itunessearch.data.remote.ITunesRemote
import com.akado.itunessearch.data.repository.TrackRepositoryImpl
import com.akado.itunessearch.domain.repository.TrackRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideTrackRepository(dataSource: ITunesRemote): TrackRepository {
        return TrackRepositoryImpl(dataSource)
    }
}