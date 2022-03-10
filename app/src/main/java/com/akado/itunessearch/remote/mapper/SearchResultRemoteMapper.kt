package com.akado.itunessearch.remote.mapper

import com.akado.itunessearch.data.model.TrackItemData
import com.akado.itunessearch.remote.model.SearchResultRemoteModel

object SearchResultRemoteMapper : RemoteMapper<SearchResultRemoteModel, TrackItemData> {

    override fun mapToData(from: SearchResultRemoteModel): TrackItemData {
        return TrackItemData(
            artistId = from.artistId,
            collectionId = from.collectionId,
            trackId = from.trackId,
            artistName = from.artistName,
            collectionName = from.collectionName,
            trackName = from.trackName,
            artworkUrl60 = from.artworkUrl60,
            isFavorite = false
        )
    }
}