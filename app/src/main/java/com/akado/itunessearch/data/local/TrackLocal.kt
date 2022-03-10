package com.akado.itunessearch.data.local

import com.akado.itunessearch.data.model.TrackItemData
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single

interface TrackLocal {

    fun existFavoriteTrack(item :TrackItemData): Boolean

    fun getFavoriteTracks(): Single<List<TrackItemData>>

    fun setFavorite(item :TrackItemData): Completable

    fun unsetFavorite(item :TrackItemData): Completable
}