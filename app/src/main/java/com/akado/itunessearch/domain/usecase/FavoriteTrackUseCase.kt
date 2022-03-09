package com.akado.itunessearch.domain.usecase

import com.akado.itunessearch.domain.model.TrackItemDomainModel
import com.akado.itunessearch.domain.repository.TrackRepository
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class FavoriteTrackUseCase @Inject constructor (
    private val repository: TrackRepository
) {
    fun requestFavoriteTrack(): Flowable<List<TrackItemDomainModel>> {
        return repository.requestFavoriteTrack()
            .toFlowable()
            .subscribeOn(Schedulers.io())
    }
}