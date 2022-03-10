package com.akado.itunessearch.local.mapper

interface LocalMapper<M, E> {

    fun mapToData(from: M): E

    fun dataToMap(from: E): M
}