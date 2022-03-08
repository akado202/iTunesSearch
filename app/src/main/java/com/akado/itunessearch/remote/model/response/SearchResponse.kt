package com.akado.itunessearch.remote.model.response

import com.akado.itunessearch.remote.model.SearchResultRemoteModel
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SearchResponse(

    @SerializedName("resultCount")
    @Expose
    val resultCount: Int,

    @SerializedName("results")
    @Expose
    val results: List<SearchResultRemoteModel>
)