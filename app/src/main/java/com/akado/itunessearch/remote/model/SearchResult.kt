package com.akado.itunessearch.remote.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SearchResult(

    @SerializedName("artistId")
    @Expose
    val artistId: Long,

    @SerializedName("collectionId")
    @Expose
    val collectionId: Long,

    @SerializedName("trackId")
    @Expose
    val trackId: Long,

    @SerializedName("artistName")
    @Expose
    val artistName: String,

    @SerializedName("collectionName")
    @Expose
    val collectionName: String,

    @SerializedName("trackName")
    @Expose
    val trackName: String,

    @SerializedName("artworkUrl60")
    @Expose
    val artworkUrl60: String,
)