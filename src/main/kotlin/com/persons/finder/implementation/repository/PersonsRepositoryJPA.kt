package com.persons.finder.implementation.repository

import com.persons.finder.implementation.repository.entity.PersonEntity
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param

interface PersonsRepositoryJPA: CrudRepository<PersonEntity, Long> {

    @Query("SELECT p FROM PersonEntity p WHERE (p.latitude > :minLat AND p.latitude < :maxLat) AND (p.longitude > :minLong AND p.longitude < :maxLong) AND p.id <> :id")
    fun getAllPersonsWithinCoordinates(
            @Param("id") searcherId: Long,
            @Param("minLat") minLatitude: Double,
            @Param("minLong") minLongitude: Double,
            @Param("maxLat") maxLatitude: Double,
            @Param("maxLong") maxLongitude: Double): List<PersonEntity>

}
