package com.akado.itunessearch.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.akado.itunessearch.domain.model.TrackItemDomainModel
import com.akado.itunessearch.domain.usecase.SearchTrackUseCase
import com.akado.itunessearch.domain.usecase.ToggleFavoriteTrackUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchTrackUseCase: SearchTrackUseCase,
    private val toggleFavoriteTrackUseCase: ToggleFavoriteTrackUseCase
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _list = MutableLiveData<List<TrackItemDomainModel>>(listOf())
    val list: LiveData<List<TrackItemDomainModel>> get() = _list

    init {
        searchTrackUseCase.requestSearchTrack()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { _list.value = it }
            .addTo(compositeDisposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    fun toggleFavoriteTrack(model: TrackItemDomainModel) {
        toggleFavoriteTrackUseCase.toggleFavoriteTrack(model)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnComplete { _list.value = list.value }
            .subscribe()
            .addTo(compositeDisposable)
    }
}