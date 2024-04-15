package com.persons.finder.implementation.presentation.dto

import com.persons.finder.domain.person.Person

data class PersonDTO (
        val id: Long,
        val name: String,
        val location: LocationDTO? = null
): BaseDTO<Person> {
    override fun convertToDomain(): Person {
       return Person(id, name, location?.convertToDomain())
    }
}

