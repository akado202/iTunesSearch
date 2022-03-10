package com.akado.itunessearch

import android.content.Context
import android.util.Log
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.akado.itunessearch.di.DbModule
import com.akado.itunessearch.local.dao.TrackDao
import com.akado.itunessearch.local.db.LocalDatabase
import com.akado.itunessearch.local.entity.TrackEntity
import com.akado.itunessearch.remote.api.ApiService
import com.akado.typiccode.di.ApiModule
import com.akado.typiccode.di.NetworkModule
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

@RunWith(AndroidJUnit4::class)
class LocalUnitTest {

    private lateinit var localDatabase: LocalDatabase
    private lateinit var trackDao: TrackDao

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        localDatabase = DbModule.provideDatabase(context)
        trackDao = localDatabase.trackDao()
    }

    @Test
    fun testLocalDatabase() {
        println("testLocalDatabase")
        Assert.assertNotNull(localDatabase)
    }

    @Test
    fun testTrackDao() {
        println("testTrackDao")
        Assert.assertNotNull(trackDao)
    }

    @Test
    fun testGetAll() {
        println("testGetAll")
        trackDao.getAll()
            .doOnSuccess { println("output") }
            .doOnSuccess { it.map { println(" - ${it.artistId} / ${it.artistName}") } }
            .test()
            .awaitDone(3, TimeUnit.SECONDS)
            .assertComplete()
    }

    @Test
    fun testInsert() {
        println("testInsert")
        val track = TrackEntity(
            0L,
            0L,
            0L,
            "artistName",
            "collectionName",
            "trackName",
            "https://is4-ssl.mzstatic.com/image/thumb/Music114/v4/64/62/b8/6462b837-977e-d5ba-7011-85ff2882ccc6/source/60x60bb.jpg",
        )
        trackDao.insert(track)
            .doOnComplete { println("Insert complete") }
            .test()
            .awaitDone(3, TimeUnit.SECONDS)
            .assertComplete()
    }

//    @Test
//    fun testDelete() {
//        println("testDelete")
//        trackDao.delete(1)
//            .doOnComplete { println("Delete complete") }
//            .test()
//            .awaitDone(3, TimeUnit.SECONDS)
//            .assertComplete()
//    }

    @Test
    fun testDeleteAll() {
        println("testDeleteAll")
        trackDao.deleteAll()
            .doOnComplete { println("DeleteAll complete") }
            .test()
            .awaitDone(3, TimeUnit.SECONDS)
            .assertComplete()
    }
}