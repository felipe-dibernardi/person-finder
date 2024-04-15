package com.persons.finder.domain.person.repository

import com.persons.finder.domain.person.Location
import com.persons.finder.domain.person.Person

interface PersonRepository {

    fun getById(id: Long): Person

    fun save(person: Person): Person

    fun getAllPersonsWithinCoordinates(id: Long, minLocation: Location, maxLocation: Location): List<Person>
}
