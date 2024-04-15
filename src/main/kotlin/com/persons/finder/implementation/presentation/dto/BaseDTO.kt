package com.persons.finder.implementation.presentation.dto

interface BaseDTO<T> {

    fun convertToDomain(): T

}
