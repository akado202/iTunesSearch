package com.akado.itunessearch.remote.api

import com.akado.itunessearch.remote.model.response.SearchResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("search")
    fun getSearch(
        @Query("term") term: String,
        @Query("entity") entity: String,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): Single<SearchResponse>
}