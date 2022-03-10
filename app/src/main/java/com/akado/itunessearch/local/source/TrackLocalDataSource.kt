package com.akado.itunessearch.local.source

import com.akado.itunessearch.data.local.TrackLocal
import com.akado.itunessearch.data.model.TrackItemData
import com.akado.itunessearch.local.db.LocalDatabase
import com.akado.itunessearch.local.mapper.FavoriteTrackItemLocalMapper
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single
import javax.inject.Inject

class TrackLocalDataSource @Inject constructor(
    private val database: LocalDatabase
) : TrackLocal {

    override fun existFavoriteTrack(item: TrackItemData): Boolean {
        return database.trackDao().get(item.trackId) != null
    }

    override fun getFavoriteTracks(): Single<List<TrackItemData>> {
        return database.trackDao()
            .getAll()
            .map { it.map(FavoriteTrackItemLocalMapper::mapToData) }
    }

    override fun setFavorite(item: TrackItemData): Completable {
        return database.trackDao()
            .insert(FavoriteTrackItemLocalMapper.dataToMap(item))
    }

    override fun unsetFavorite(item: TrackItemData): Completable {
        return database.trackDao()
            .deleteByTrackId(item.trackId)
    }

}