package com.akado.itunessearch.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.akado.itunessearch.local.dao.TrackDao
import com.akado.itunessearch.local.entity.TrackEntity

@Database(entities = [TrackEntity::class], exportSchema = false, version = 1)
abstract class LocalDatabase : RoomDatabase() {
    abstract fun trackDao(): TrackDao
}