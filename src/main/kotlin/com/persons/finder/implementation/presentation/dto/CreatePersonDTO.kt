package com.persons.finder.implementation.presentation.dto

import com.persons.finder.domain.person.Person

class CreatePersonDTO(
        val name: String,
        val location: LocationDTO?
) {
    fun convertToDomain(): Person {
        return Person(name = name, location = location?.convertToDomain())
    }
}
