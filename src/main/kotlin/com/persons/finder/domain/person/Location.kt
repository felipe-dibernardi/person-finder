package com.persons.finder.domain.person

import kotlin.math.*

class Location(
    val latitude: Double,
    val longitude: Double,
) {

    private val EARTH_RADIUS: Double = 6371.0

    fun calculateLowerBoundaryCoordinates(radius: Double): Location {
        val latitudeInRadians: Double = Math.toRadians(latitude)
        val longitudeInRadians: Double = Math.toRadians(longitude)
        val radiusDegrees: Double = radius / EARTH_RADIUS
        val deltaLon = asin(sin(radiusDegrees) / cos(latitudeInRadians))

        val minLatitude = Math.toDegrees(latitudeInRadians - radiusDegrees)

        val minLongitude: Double = Math.toDegrees(longitudeInRadians - deltaLon)

        return Location(minLatitude, minLongitude)
    }

    fun calculateUpperBoundaryCoordinates(radius: Double): Location {
        val latitudeInRadians: Double = Math.toRadians(latitude)
        val longitudeInRadians: Double = Math.toRadians(longitude)
        val radiusDegrees: Double = radius / EARTH_RADIUS

        val deltaLon = asin(sin(radiusDegrees) / cos(latitudeInRadians))

        val maxLatitude = Math.toDegrees(latitudeInRadians + radiusDegrees)
        val maxLongitude: Double = Math.toDegrees(longitudeInRadians + deltaLon)

        return Location(maxLatitude, maxLongitude)
    }

    fun calculateDistanceInKilometer(location: Location): Double {
        val latDistance = Math.toRadians(latitude - location.latitude)
        val lonDistance = Math.toRadians(longitude - location.longitude)
        val a = sin(latDistance / 2) * sin(latDistance / 2) +
                cos(Math.toRadians(latitude)) * cos(Math.toRadians(location.latitude)) *
                sin(lonDistance / 2) * sin(lonDistance / 2)
        val c = 2 * atan2(sqrt(a), sqrt(1 - a))
        return EARTH_RADIUS * c
    }
}
