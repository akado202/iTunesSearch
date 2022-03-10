package com.akado.itunessearch.domain.usecase

import com.akado.itunessearch.domain.model.TrackItemDomainModel
import com.akado.itunessearch.domain.repository.TrackRepository
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ToggleFavoriteTrackUseCase @Inject constructor(
    private val repository: TrackRepository
) {
    fun setFavoriteTrack(model: TrackItemDomainModel): Completable {
        return repository.setFavoriteTrack(model)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun unsetFavoriteTrack(model: TrackItemDomainModel): Completable {
        return repository.unsetFavoriteTrack(model)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}