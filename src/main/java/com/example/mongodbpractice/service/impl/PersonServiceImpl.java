package com.example.mongodbpractice.service.impl;

import com.example.mongodbpractice.model.Person;
import com.example.mongodbpractice.repository.PersonRepo;
import com.example.mongodbpractice.service.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {
    @Autowired
    private PersonRepo personRepo;
    @Override
    public List<Person> getAllPersons() {
        List<Person> personList = personRepo.findAll();
        return personList;
    }

    @Override
    public Person savePerson(Person person) {
        Person savedPerson = personRepo.save(person);
        return savedPerson;
    }

    @Override
    public Optional<Person> getPerson(Object id) {
        Optional<Person> findPerson = personRepo.findById(id);
        return findPerson;
    }

    @Override
    public String deletePerson(Object id) {
        personRepo.deleteById(id);
        return "Person delete query was successful";
    }

    @Override
    public Optional<Person> updatePerson(Object id, Person person) {
        Optional<Person> optionalPerson = personRepo.findById(id);

        if (optionalPerson.isPresent()) {
            Person existingPerson = optionalPerson.get();

            existingPerson.setFirstName(person.getFirstName());
            existingPerson.setLastName(person.getLastName());
            existingPerson.setHobbies(person.getHobbies());
            existingPerson.setAddresses(person.getAddresses());

            // Save the updated person to the repository
            personRepo.save(existingPerson);

            return Optional.of(existingPerson);
        } else {
            System.out.println("Person not found");
            return Optional.empty();
        }
    }

}
