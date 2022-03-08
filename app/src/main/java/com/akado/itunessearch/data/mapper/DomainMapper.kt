package com.akado.itunessearch.data.mapper

interface DomainMapper<in V, out D> {

    fun mapToModel(from: V): D
}