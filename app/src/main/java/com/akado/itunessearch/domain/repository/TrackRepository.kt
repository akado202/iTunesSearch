package com.akado.itunessearch.domain.repository

import com.akado.itunessearch.domain.model.TrackItemDomainModel
import io.reactivex.Single

interface TrackRepository {

    fun requestSearchTrack(
        term: String,
        entity: String,
        limit: Int,
        offset: Int
    ): Single<List<TrackItemDomainModel>>

    fun requestFavoriteTrack(): Single<List<TrackItemDomainModel>>
}