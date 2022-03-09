package com.akado.itunessearch.local.mapper

import com.akado.itunessearch.data.model.TrackItemData
import com.akado.itunessearch.local.entity.TrackEntity

object FavoriteTrackItemLocalMapper : LocalMapper<TrackEntity, TrackItemData> {

    override fun mapToData(from: TrackEntity): TrackItemData {
        return TrackItemData(
            artistId = from.artistId,
            collectionId = from.collectionId,
            trackId = from.trackId,
            artistName = from.artistName,
            collectionName = from.collectionName,
            trackName = from.trackName,
            artworkUrl60 = from.artworkUrl60,
        )
    }
}