package com.akado.itunessearch.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Track")
class TrackEntity(
    val artistId: Long,
    val collectionId: Long,
    val trackId: Long,
    val artistName: String,
    val collectionName: String,
    val trackName: String,
    val artworkUrl60: String,
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 1L
}