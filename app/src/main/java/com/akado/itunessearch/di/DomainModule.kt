package com.akado.itunessearch.di

import com.akado.itunessearch.domain.repository.TrackRepository
import com.akado.itunessearch.domain.usecase.SearchTrackUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Provides
    @Singleton
    fun provideSearchTrackUseCase(repository: TrackRepository): SearchTrackUseCase {
        return SearchTrackUseCase(repository)
    }
}