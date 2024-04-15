package com.persons.finder.implementation.presentation

import com.persons.finder.domain.person.service.PersonsService
import com.persons.finder.implementation.presentation.dto.CreatePersonDTO
import com.persons.finder.implementation.presentation.dto.LocationDTO
import com.persons.finder.implementation.presentation.dto.PersonDTO
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1/persons")
class PersonController (val personsService: PersonsService) {

    /*
        TODO PUT API to update/create someone's location using latitude and longitude
        (JSON) Body
     */
    @PutMapping("location/{id}")
    fun updateLocation(@PathVariable("id") id: Long,
                       @RequestBody locationDTO: LocationDTO): ResponseEntity<Any> {
        personsService.updateLocation(id, locationDTO.convertToDomain())
        return ResponseEntity.ok().build()
    }

    /*
        TODO POST API to create a 'person'
        (JSON) Body and return the id of the created entity
    */
    @PostMapping
    fun create(@RequestBody personDTO: CreatePersonDTO): ResponseEntity<Long> {
        return ResponseEntity.status(201).body(personsService.save(personDTO.convertToDomain()))
    }

    /*
        TODO GET API to retrieve people around query location with a radius in KM, Use query param for radius.
        TODO API just return a list of persons ids (JSON)
        // Example
        // John wants to know who is around his location within a radius of 10km
        // API would be called using John's id and a radius 10km
     */
    @GetMapping("{id}/findAround")
    fun findAround(@PathVariable("id") id: Long,
                   @RequestParam("radius") radius: Double?): ResponseEntity<List<Long>> {
        return ResponseEntity
                .ok(this.personsService.getAllPersonsWithinCoordinates(id, radius))
    }

    /*
        TODO GET API to retrieve a person or persons name using their ids
        // Example
        // John has the list of people around them, now they need to retrieve everybody's names to display in the app
        // API would be called using person or persons ids
     */
    @GetMapping("")
    fun getNames(@RequestBody ids: List<Long>): ResponseEntity<List<PersonDTO>> {
        return ResponseEntity.ok(personsService.getPersons(ids)
                .map { PersonDTO(it.id!!, it.name) })
    }

}
