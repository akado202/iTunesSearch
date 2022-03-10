package com.akado.itunessearch.data.repository

import com.akado.itunessearch.data.local.TrackLocal
import com.akado.itunessearch.data.mapper.TrackItemDomainMapper
import com.akado.itunessearch.data.remote.ITunesRemote
import com.akado.itunessearch.domain.model.TrackItemDomainModel
import com.akado.itunessearch.domain.repository.TrackRepository
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class TrackRepositoryImpl @Inject constructor(
    private val remote: ITunesRemote,
    private val local: TrackLocal
) : TrackRepository {

    override fun requestSearchTrack(
        term: String,
        entity: String,
        limit: Int,
        offset: Int
    ): Single<List<TrackItemDomainModel>> {
        return remote.getSearch(term, entity, limit, offset)
            .map { it.map(TrackItemDomainMapper::mapToModel) }
    }

    override fun requestFavoriteTrack(): Single<List<TrackItemDomainModel>> {
        return local.getFavoriteTracks()
            .map { it.map(TrackItemDomainMapper::mapToModel) }
    }

    override fun setFavoriteTrack(model: TrackItemDomainModel): Completable {
        return local.setFavorite(TrackItemDomainMapper.modelToMap(model))
    }

    override fun unsetFavoriteTrack(model: TrackItemDomainModel): Completable {
        return local.unsetFavorite(TrackItemDomainMapper.modelToMap(model))
    }
}