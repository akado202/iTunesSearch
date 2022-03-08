package com.akado.itunessearch.remote.source

import com.akado.itunessearch.data.model.TrackItemData
import com.akado.itunessearch.data.remote.ITunesRemote
import com.akado.itunessearch.remote.api.ApiService
import com.akado.itunessearch.remote.mapper.SearchResultRemoteMapper
import io.reactivex.Single
import javax.inject.Inject

class ITunesRemoteDataSource @Inject constructor(
    private val service: ApiService
) : ITunesRemote {

    override fun getSearch(
        term: String,
        entity: String,
        limit: Int,
        offset: Int
    ): Single<List<TrackItemData>> {
        return service.getSearch(term, entity, limit, offset)
            .map { it.results.map(SearchResultRemoteMapper::mapToData) }
    }
}