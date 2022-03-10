package com.akado.itunessearch

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.akado.itunessearch.data.remote.ITunesRemote
import com.akado.itunessearch.di.DataModule
import com.akado.itunessearch.di.DbModule
import com.akado.itunessearch.di.LocalSourceModule
import com.akado.itunessearch.di.RemoteSourceModule
import com.akado.itunessearch.domain.repository.TrackRepository
import com.akado.itunessearch.remote.api.ApiService
import com.akado.typiccode.di.ApiModule
import com.akado.typiccode.di.NetworkModule
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

@RunWith(AndroidJUnit4::class)
class RepositoryUnitTest {

    private lateinit var repository: TrackRepository

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()

        val retrofit = NetworkModule.provideRetrofit()
        val apiService = ApiModule.provideApiService(retrofit)

        val localDatabase = DbModule.provideDatabase(context)

        repository = DataModule.provideTrackRepository(
            RemoteSourceModule.provideITunesDataSource(apiService),
            LocalSourceModule.provideITunesDataSource(localDatabase)
        )
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