package com.akado.itunessearch.data.mapper

interface DomainMapper<V, D> {

    fun mapToModel(from: V): D

    fun modelToMap(from: D): V
}