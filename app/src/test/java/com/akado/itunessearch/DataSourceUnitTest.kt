package com.akado.itunessearch

import com.akado.itunessearch.data.remote.ITunesRemote
import com.akado.itunessearch.di.RemoteSourceModule
import com.akado.itunessearch.remote.api.ApiService
import com.akado.typiccode.di.ApiModule
import com.akado.typiccode.di.NetworkModule
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

class DataSourceUnitTest {

    private lateinit var retrofit: Retrofit
    private lateinit var apiService: ApiService
    private lateinit var remote: ITunesRemote

    @Before
    fun setup() {
        retrofit = NetworkModule.provideRetrofit()
        apiService = ApiModule.provideApiService(retrofit)
        remote = RemoteSourceModule.provideITunesDataSource(apiService)
    }

    @Test
    fun testGetSearch() {
        println("testGetSearch")
        remote.getSearch("greenday", "song", 10, 0)
            .doOnSuccess { println("output : $it") }
            .test()
            .awaitDone(3, TimeUnit.SECONDS)
            .assertValue { it.isNotEmpty() }
            .assertComplete()
    }
}