package com.persons.finder.implementation.repository.entity

interface BaseEntity<T> {

    fun convertToDomain(): T

}
