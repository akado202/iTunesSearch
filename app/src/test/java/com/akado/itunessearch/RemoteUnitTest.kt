package com.akado.itunessearch

import com.akado.itunessearch.remote.api.ApiService
import com.akado.typiccode.di.ApiModule
import com.akado.typiccode.di.NetworkModule
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

class RemoteUnitTest {

    private lateinit var retrofit: Retrofit
    private lateinit var apiService: ApiService

    @Before
    fun setup() {
        retrofit = NetworkModule.provideRetrofit()
        apiService = ApiModule.provideApiService(retrofit)
    }

    @Test
    fun testGetSearch() {
        println("testGetSearch")
        apiService.getSearch("greenday", "song", 10, 0)
            .doOnSuccess { println("output : $it") }
            .map { it.results }
            .test()
            .awaitDone(3, TimeUnit.SECONDS)
            .assertValue { it.isNotEmpty() }
            .assertComplete()
    }
}