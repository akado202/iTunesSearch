package com.akado.itunessearch.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.akado.itunessearch.local.entity.TrackEntity
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface TrackDao {

    @Query("SELECT * FROM Track WHERE trackId=:trackId")
    fun get(trackId: Long): TrackEntity?

    @Query("SELECT * FROM Track")
    fun getAll(): Single<List<TrackEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entity: TrackEntity): Completable

    @Query("DELETE FROM Track WHERE trackId=:trackId")
    fun deleteByTrackId(trackId: Long): Completable

    @Query("DELETE FROM Track")
    fun deleteAll(): Completable
}