package com.example.mongodbpractice.controller;

import com.example.mongodbpractice.model.Person;
import com.example.mongodbpractice.service.impl.PersonServiceImpl;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/person")
public class PersonController {

    @Autowired
    private PersonServiceImpl personService;

    @GetMapping("/")
    public List<Person> getAllPersons() {
        return personService.getAllPersons();
    }

    @PostMapping("/")
    public Person savePerson(@RequestBody Person person) {
        System.out.println(person);
        return personService.savePerson(person);
    }

    @GetMapping("/{personId}")
    public Optional<Person> getPerson(@PathVariable Object personId) {
        return personService.getPerson(personId);
    }

    @DeleteMapping("/{personId}")
    public String deletePerson(@PathVariable Object personId) {
        return personService.deletePerson(personId);
    }

    @PutMapping("/{personId}")
    public Optional<Person> updatedPerson(@PathVariable Object personId, @RequestBody Person person) {
        Optional<Person> updatedPerson = personService.updatePerson(personId, person);
        return updatedPerson;
    }

}
