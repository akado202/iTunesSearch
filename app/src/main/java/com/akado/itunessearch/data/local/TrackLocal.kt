package com.akado.itunessearch.data.local

import com.akado.itunessearch.data.model.TrackItemData
import io.reactivex.Single

interface TrackLocal {

    fun getFavorite(): Single<List<TrackItemData>>
}