package com.akado.itunessearch.ui.common

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.akado.itunessearch.domain.model.TrackItemDomainModel
import com.akado.itunessearch.domain.usecase.FavoriteTrackUseCase
import com.akado.itunessearch.domain.usecase.SearchTrackUseCase
import com.akado.itunessearch.domain.usecase.ToggleFavoriteTrackUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val searchTrackUseCase: SearchTrackUseCase,
    private val favoriteTrackUseCase: FavoriteTrackUseCase,
    private val toggleFavoriteTrackUseCase: ToggleFavoriteTrackUseCase
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _searchList = MutableLiveData<ArrayList<TrackItemDomainModel>>(ArrayList())
    val searchList: LiveData<ArrayList<TrackItemDomainModel>> get() = _searchList

    private val _favoriteList = MutableLiveData<ArrayList<TrackItemDomainModel>>(ArrayList())
    val favoriteList: LiveData<ArrayList<TrackItemDomainModel>> get() = _favoriteList

    init {
        favoriteTrackUseCase.requestFavoriteTrack()
            .doOnNext { _favoriteList.value = ArrayList(it) }
            .flatMap { searchTrackUseCase.requestSearchTrack() }
            .doOnNext { _searchList.value = ArrayList(it) }
            .subscribe()
            .addTo(compositeDisposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    private fun getFavoriteTrackIndex(model: TrackItemDomainModel):Int {
        var target = -1
        var idx = 0;
        favoriteList.value?.forEach {
            if (it.trackId == model.trackId) {
                target = idx
                return@forEach
            }
            idx++
        }
        return target
    }

    fun isFavoriteTrack(model: TrackItemDomainModel): Boolean  = getFavoriteTrackIndex(model) >= 0

    fun toggleFavoriteTrack(model: TrackItemDomainModel) {
        val idx = getFavoriteTrackIndex(model)
        if (idx >= 0) {
            toggleFavoriteTrackUseCase.unsetFavoriteTrack(model)
                .doOnComplete { _favoriteList.value?.removeAt(idx) }
                .doOnComplete { _favoriteList.value = favoriteList.value }
                .doOnComplete { refresh() }
                .subscribe()
                .addTo(compositeDisposable)
        } else {
            toggleFavoriteTrackUseCase.setFavoriteTrack(model)
                .doOnComplete { _favoriteList.value?.add(0, model) }
                .doOnComplete { _favoriteList.value = favoriteList.value }
                .doOnComplete { refresh() }
                .subscribe()
                .addTo(compositeDisposable)
        }
    }

    fun refresh() {
        _searchList.value = _searchList.value
    }
}