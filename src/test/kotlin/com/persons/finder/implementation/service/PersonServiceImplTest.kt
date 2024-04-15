package com.persons.finder.implementation.service

import com.persons.finder.domain.person.Location
import com.persons.finder.domain.person.Person
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
class PersonServiceImplTest @Autowired constructor(val personsServiceImpl: PersonsServiceImpl) {

    var firstPersonId: Long? = null

    var secondPersonId: Long? = null

    var thirdPersonId: Long? = null

    @BeforeAll
    internal fun beforeAll() {
        firstPersonId = personsServiceImpl.save(
                Person(name = "John Doe", location = Location(-36.79234565832773, 174.75825549299586)))
        secondPersonId = personsServiceImpl.save(
                Person(name = "Jane Smith", location = Location(-37.01306199957321, 174.90711442170561)))
        thirdPersonId = personsServiceImpl.save(
                Person(name = "Jack Brown", location = Location(-37.01612984497013, 174.96768967278746)))
    }

    @Test
    fun `Should not return persons when no one is within boundary`() {

        val personsWithinRangeFromFirst = personsServiceImpl.getAllPersonsWithinCoordinates(firstPersonId!!, 10.0)
        val personsWithinRangeFromSecond = personsServiceImpl.getAllPersonsWithinCoordinates(secondPersonId!!, 10.0)

        Assertions.assertEquals(0, personsWithinRangeFromFirst.size)
        Assertions.assertEquals(1, personsWithinRangeFromSecond.size)
        Assertions.assertEquals(thirdPersonId, personsWithinRangeFromSecond.get(0))
    }

    @Test
    fun `Should return persons when they are within boundary ordered by closest to farthest`() {
        val personsWithinRangeFromFirst = personsServiceImpl.getAllPersonsWithinCoordinates(firstPersonId!!, 50.0)
        val personsWithinRangeFromSecond = personsServiceImpl.getAllPersonsWithinCoordinates(secondPersonId!!, 50.0)

        Assertions.assertEquals(2, personsWithinRangeFromFirst.size)
        Assertions.assertEquals(secondPersonId, personsWithinRangeFromFirst.get(0))
        Assertions.assertEquals(2, personsWithinRangeFromSecond.size)
        Assertions.assertEquals(thirdPersonId, personsWithinRangeFromSecond.get(0))
        Assertions.assertEquals(firstPersonId, personsWithinRangeFromSecond.get(1))
    }

}
