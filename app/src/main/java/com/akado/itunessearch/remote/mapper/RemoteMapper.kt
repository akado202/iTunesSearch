package com.akado.itunessearch.remote.mapper

interface RemoteMapper<in M, out E> {

    fun mapToData(from: M): E

}