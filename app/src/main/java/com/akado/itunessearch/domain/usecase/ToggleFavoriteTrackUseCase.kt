package com.akado.itunessearch.domain.usecase

import com.akado.itunessearch.domain.model.TrackItemDomainModel
import com.akado.itunessearch.domain.repository.TrackRepository
import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ToggleFavoriteTrackUseCase @Inject constructor(
    private val repository: TrackRepository
) {
    fun toggleFavoriteTrack(model: TrackItemDomainModel): Completable {
        return repository.toggleFavoriteTrack(model)
            .subscribeOn(Schedulers.io())
            .doOnComplete { model.isFavorite = !model.isFavorite }
    }
}