package com.akado.itunessearch.local.mapper

interface LocalMapper<in M, out E> {

    fun mapToData(from: M): E

}