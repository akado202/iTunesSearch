package com.akado.itunessearch.di

import android.content.Context
import androidx.room.Room
import com.akado.itunessearch.local.db.LocalDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DbModule {

    @Provides
    @Named("DatabaseName")
    fun provideDatabaseName() = "com.akado.itunessearch.local"

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext applicationContext: Context
    ): LocalDatabase {
        return Room.databaseBuilder(
            applicationContext,
            LocalDatabase::class.java,
            provideDatabaseName()
        ).build()
    }
}