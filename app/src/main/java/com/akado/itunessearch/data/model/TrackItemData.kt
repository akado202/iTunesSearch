package com.akado.itunessearch.data.model

data class TrackItemData(
    val artistId: Long,
    val collectionId: Long,
    val trackId: Long,
    val artistName: String,
    val collectionName: String,
    val trackName: String,
    val artworkUrl60: String,
)