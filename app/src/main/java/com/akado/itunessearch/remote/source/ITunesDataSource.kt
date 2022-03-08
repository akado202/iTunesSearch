package com.akado.itunessearch.remote.source

import com.akado.itunessearch.data.remote.ITunesRemote
import com.akado.itunessearch.remote.api.ApiService
import io.reactivex.Single
import javax.inject.Inject

class ITunesDataSource @Inject constructor(
    private val service: ApiService
) : ITunesRemote {


}