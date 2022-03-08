package com.akado.itunessearch

import com.akado.itunessearch.data.remote.ITunesRemote
import com.akado.itunessearch.di.DataModule
import com.akado.itunessearch.di.RemoteSourceModule
import com.akado.itunessearch.domain.repository.TrackRepository
import com.akado.itunessearch.remote.api.ApiService
import com.akado.typiccode.di.ApiModule
import com.akado.typiccode.di.NetworkModule
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

class RepositoryUnitTest {

    private lateinit var retrofit: Retrofit
    private lateinit var apiService: ApiService
    private lateinit var remote: ITunesRemote
    private lateinit var repository: TrackRepository

    @Before
    fun setup() {
        retrofit = NetworkModule.provideRetrofit()
        apiService = ApiModule.provideApiService(retrofit)
        remote = RemoteSourceModule.provideITunesDataSource(apiService)
        repository = DataModule.provideTrackRepository(remote)
    }

    @Test
    fun testRequestSearchTrack() {
        println("testGetSearch")
        repository.requestSearchTrack("greenday", "song", 10, 0)
            .doOnSuccess { println("output : $it") }
            .test()
            .awaitDone(3, TimeUnit.SECONDS)
            .assertValue { it.isNotEmpty() }
            .assertComplete()
    }
}