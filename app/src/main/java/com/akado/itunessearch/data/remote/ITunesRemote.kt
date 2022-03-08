package com.akado.itunessearch.data.remote

import com.akado.itunessearch.data.model.TrackItemData
import io.reactivex.Single

interface ITunesRemote {

    fun getSearch(
        term: String,
        entity: String,
        limit: Int,
        offset: Int
    ): Single<List<TrackItemData>>
}