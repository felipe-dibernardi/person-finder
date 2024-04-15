package com.persons.finder.implementation.service

import com.persons.finder.domain.person.Location
import com.persons.finder.domain.person.Person
import com.persons.finder.domain.person.exception.LocationNotSetException
import com.persons.finder.domain.person.exception.PersonNotFoundException
import com.persons.finder.domain.person.repository.PersonRepository
import com.persons.finder.domain.person.service.PersonsService
import org.springframework.stereotype.Service

@Service
class PersonsServiceImpl(val personRepository: PersonRepository) : PersonsService {

    override fun getById(id: Long): Person {
        return personRepository.getById(id)
    }

    override fun getPersons(ids: List<Long>): List<Person> {
        return ids.mapNotNull {
            try {
                this.getById(it)
            } catch (error: PersonNotFoundException) {
                null
            }
        }
    }

    override fun save(person: Person): Long {
        return personRepository.save(person).id ?: throw RuntimeException("No id for Person")
    }

    override fun updateLocation(id: Long, location: Location) {
        val person = personRepository.getById(id)
        person.location = location
        personRepository.save(person)
    }

    override fun getAllPersonsWithinCoordinates(id: Long, radius: Double?):
            List<Long> {
        val person = this.getById(id)
        val centerLocation = person.location ?: throw LocationNotSetException("Person with id $id does not have location set")
        val lowerBoundaryLocation = centerLocation.calculateLowerBoundaryCoordinates(radius ?: 0.0)
        val upperBoundaryLocation = centerLocation.calculateUpperBoundaryCoordinates(radius ?: 0.0)
        val personsWithinCoordinates = personRepository.getAllPersonsWithinCoordinates(id, lowerBoundaryLocation, upperBoundaryLocation)
        val personsSorted = personsWithinCoordinates.sortedWith(compareBy { it.location!!.calculateDistanceInKilometer(centerLocation) })
        return personsSorted.mapNotNull { it.id }
    }
}
