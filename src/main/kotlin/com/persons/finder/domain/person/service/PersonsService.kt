package com.persons.finder.domain.person.service

import com.persons.finder.domain.person.Location
import com.persons.finder.domain.person.Person

interface PersonsService {
    fun getById(id: Long): Person
    fun getPersons(ids: List<Long>): List<Person>
    fun save(person: Person): Long
    fun updateLocation(id: Long, location: Location)
    fun getAllPersonsWithinCoordinates(id: Long, radius: Double?): List<Long>
}
