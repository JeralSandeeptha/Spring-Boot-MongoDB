package com.example.mongodbpractice.service.services;

import com.example.mongodbpractice.model.Person;

import java.util.List;
import java.util.Optional;

public interface PersonService {
    public List<Person> getAllPersons();

    public Person savePerson(Person person);

    public Optional<Person> getPerson(Object id);

    public String deletePerson(Object id);

    public Object updatePerson(Object id, Person person);

}
