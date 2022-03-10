package com.akado.itunessearch.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.akado.itunessearch.domain.model.TrackItemDomainModel
import com.akado.itunessearch.domain.usecase.FavoriteTrackUseCase
import com.akado.itunessearch.domain.usecase.SearchTrackUseCase
import com.akado.itunessearch.domain.usecase.ToggleFavoriteTrackUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import java.util.*
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val favoriteTrackUseCase: FavoriteTrackUseCase,
    private val toggleFavoriteTrackUseCase: ToggleFavoriteTrackUseCase
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _list = MutableLiveData<List<TrackItemDomainModel>>(listOf())
    val list: LiveData<List<TrackItemDomainModel>> get() = _list

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    fun request() {
        favoriteTrackUseCase.requestFavoriteTrack()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { _list.value = it }
            .addTo(compositeDisposable)
    }

    fun toggleFavoriteTrack(model: TrackItemDomainModel) {
        toggleFavoriteTrackUseCase.toggleFavoriteTrack(model)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnComplete { request() }
            .subscribe()
            .addTo(compositeDisposable)
    }
}