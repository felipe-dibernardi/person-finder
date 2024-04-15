package com.persons.finder.implementation.repository

import com.persons.finder.domain.person.Location
import com.persons.finder.domain.person.Person
import com.persons.finder.domain.person.exception.PersonNotFoundException
import com.persons.finder.domain.person.repository.PersonRepository
import com.persons.finder.implementation.repository.entity.PersonEntity
import org.springframework.stereotype.Repository

@Repository
class PersonsRepositoryImpl(
        private val personsRepositoryJPA: PersonsRepositoryJPA
): PersonRepository {

    override fun getById(id: Long): Person {
        val personEntity =
                personsRepositoryJPA.findById(id)
                        .orElseThrow { PersonNotFoundException("Person with id $id not found.") }
        return personEntity.convertToDomain()
    }

    override fun save(person: Person): Person {
        return personsRepositoryJPA
                .save(PersonEntity(person.name, person.location?.latitude, person.location?.longitude, person.id))
                .convertToDomain()
    }

    override fun getAllPersonsWithinCoordinates(id: Long, minLocation: Location, maxLocation: Location): List<Person> {
        val personList: List<PersonEntity> =
                personsRepositoryJPA.getAllPersonsWithinCoordinates(
                        id,
                        minLocation.latitude,
                        minLocation.longitude,
                        maxLocation.latitude,
                        maxLocation.longitude)
        return personList.map { personEntity -> personEntity.convertToDomain() }
    }
}
