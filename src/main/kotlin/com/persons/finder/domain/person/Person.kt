package com.persons.finder.domain.person

data class Person(
        val id: Long? = null,
        val name: String,
        var location: Location? = null
)
