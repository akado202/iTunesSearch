package com.akado.typiccode.di

import com.akado.itunessearch.BuildConfig
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val BASE_URL = "https://itunes.apple.com/"
    private const val CONNECT_TIME = 3000L
    private const val READ_TIME = 3000L

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(provideOkHttp())
            .addCallAdapterFactory(provideRxAdapter())
            .addConverterFactory(GsonConverterFactory.create(provideGson()))
            .build()
    }

    @Provides
    @Singleton
    fun provideRxAdapter(): RxJava2CallAdapterFactory {
        return RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io())
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder().setLenient()
//            .setDateFormat(GSON_DATE_FORMAT)
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create()
    }

    @Provides
    @Singleton
    fun provideOkHttpLogging(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.NONE
//            level = if (BuildConfig.DEBUG) {
//                HttpLoggingInterceptor.Level.BODY
//            } else {
//                HttpLoggingInterceptor.Level.NONE
//            }
        }
    }

    @Provides
    @Singleton
    fun provideOkHttp(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(CONNECT_TIME, TimeUnit.SECONDS)
            .readTimeout(READ_TIME, TimeUnit.SECONDS)
            .addInterceptor(provideOkHttpLogging())
            .build()
    }
}