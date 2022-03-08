package com.akado.itunessearch.domain.usecase

import com.akado.itunessearch.domain.model.TrackItemDomainModel
import com.akado.itunessearch.domain.repository.TrackRepository
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SearchTrackUseCase @Inject constructor (
    private val repository: TrackRepository
) {
    fun requestSearchTrack(): Flowable<List<TrackItemDomainModel>> {
        return repository.requestSearchTrack("greenday", "song", 30, 0)
            .toFlowable()
            .subscribeOn(Schedulers.io())
    }
}