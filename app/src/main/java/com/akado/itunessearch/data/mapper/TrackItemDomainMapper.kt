package com.akado.itunessearch.data.mapper

import com.akado.itunessearch.data.model.TrackItemData
import com.akado.itunessearch.domain.model.TrackItemDomainModel

object TrackItemDomainMapper : DomainMapper<TrackItemData, TrackItemDomainModel> {

    override fun mapToModel(from: TrackItemData): TrackItemDomainModel {
        return TrackItemDomainModel(
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