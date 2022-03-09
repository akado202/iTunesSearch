package com.akado.itunessearch.local.source

import com.akado.itunessearch.data.local.TrackLocal
import com.akado.itunessearch.data.model.TrackItemData
import com.akado.itunessearch.local.db.LocalDatabase
import com.akado.itunessearch.local.mapper.FavoriteTrackItemLocalMapper
import io.reactivex.Single
import javax.inject.Inject

class TrackLocalDataSource @Inject constructor(
    private val database: LocalDatabase
) : TrackLocal {

    override fun getFavorite(): Single<List<TrackItemData>> {
        return database.trackDao()
            .getAll()
            .map { it.map(FavoriteTrackItemLocalMapper::mapToData) }
    }

}