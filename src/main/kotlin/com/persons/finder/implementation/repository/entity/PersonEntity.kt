package com.persons.finder.implementation.repository.entity

import com.persons.finder.domain.person.Location
import com.persons.finder.domain.person.Person
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class PersonEntity(
        @Column(nullable = false)
        var name: String,
        var latitude: Double?,
        var longitude: Double?,
        @Id
        @GeneratedValue
        var id: Long? = null
) : BaseEntity<Person> {

    override fun convertToDomain(): Person {
        if (latitude != null && longitude != null) {
            return Person(id, name, Location(latitude!!, longitude!!))
        }
        return Person(id = this.id, name = this.name)
    }
}
