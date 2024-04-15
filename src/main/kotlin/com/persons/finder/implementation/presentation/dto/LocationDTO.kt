package com.persons.finder.implementation.presentation.dto

import com.persons.finder.domain.person.Location

data class LocationDTO(
        val latitude: Double,
        val longitude: Double
): BaseDTO<Location> {
    override fun convertToDomain(): Location {
        return Location(latitude, longitude)
    }
}
