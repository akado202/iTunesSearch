package com.akado.itunessearch.domain.model

data class TrackItemDomainModel(
    val artistId: Long,
    val collectionId: Long,
    val trackId: Long,
    val artistName: String,
    val collectionName: String,
    val trackName: String,
    val artworkUrl60: String,
    var isFavorite: Boolean,
)