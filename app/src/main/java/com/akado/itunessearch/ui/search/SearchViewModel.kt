package com.akado.itunessearch.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.akado.itunessearch.domain.model.TrackItemDomainModel
import com.akado.itunessearch.domain.usecase.SearchTrackUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import java.util.*
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    useCase: SearchTrackUseCase
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _list = MutableLiveData<List<TrackItemDomainModel>>(Collections.emptyList())
    val list: LiveData<List<TrackItemDomainModel>> get() = _list

    init {
        useCase.requestSearchTrack()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { _list.value = it }
            .addTo(compositeDisposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}