package com.example.mongodbpractice.controller;

import com.example.mongodbpractice.model.Person;
import com.example.mongodbpractice.service.impl.PersonServiceImpl;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/person")
public class PersonController {

    @Autowired
    private PersonServiceImpl personService;

    @GetMapping("/")
    public ResponseEntity<Object> getAllPersons() {
        try {
            List<Person> personList = personService.getAllPersons();

            HashMap<String, Object> responseBody = new HashMap<>();
            responseBody.put("data", personList);
            responseBody.put("statusCode", HttpStatus.OK.value());
            responseBody.put("message", "Get all persons query was successful");

            return ResponseEntity.ok().body(responseBody);
        }catch(Exception ex) {
            System.out.println(ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of(
                            "statusCode", HttpStatus.INTERNAL_SERVER_ERROR.value(),
                            "message", "Get all persons query internal server errror",
                            "error", ex.getMessage()));
        }
    }

    @PostMapping("/")
    public ResponseEntity<Object> savePerson(@RequestBody Person person) {
        try {
            Person savedPerson = personService.savePerson(person);

            HashMap<String, Object> responseBody = new HashMap<>();
            responseBody.put("data", savedPerson);
            responseBody.put("statusCode", HttpStatus.CREATED.value());
            responseBody.put("message", "Create new person query was successful");

            return ResponseEntity.ok().body(responseBody);
        }catch (Exception ex){
            System.out.println(ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of(
                            "statusCode", HttpStatus.INTERNAL_SERVER_ERROR.value(),
                            "message", "Create new person query internal server error",
                            "error", ex.getMessage()));
        }
    }

    @GetMapping("/{personId}")
    public ResponseEntity<Object> getPerson(@PathVariable Object personId) {
        try {
            Optional<Person> findPerson = personService.getPerson(personId);

            if (!findPerson.isPresent()){
                HashMap<String, Object> responseBody = new HashMap<>();
                responseBody.put("statusCode", HttpStatus.NOT_FOUND.value());
                responseBody.put("message", "Get person query was failed. No person found.");
                return ResponseEntity.ok().body(responseBody);
            }else {
                HashMap<String, Object> responseBody = new HashMap<>();
                responseBody.put("data", findPerson);
                responseBody.put("statusCode", HttpStatus.OK.value());
                responseBody.put("message", "Get person query was successful");
                return ResponseEntity.ok().body(responseBody);
            }
        }catch (Exception ex){
            System.out.println(ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of(
                            "statusCode", HttpStatus.INTERNAL_SERVER_ERROR.value(),
                            "message", "Get person query internal server error",
                            "error", ex.getMessage()));
        }
    }

    @DeleteMapping("/{personId}")
    public ResponseEntity<Object> deletePerson(@PathVariable Object personId) {
        try {
            Optional<Person> findPerson = personService.getPerson(personId);

            if (!findPerson.isPresent()){
                HashMap<String, Object> responseBody = new HashMap<>();
                responseBody.put("statusCode", HttpStatus.NOT_FOUND.value());
                responseBody.put("message", "Delete person query was failed. No person found.");
                return ResponseEntity.ok().body(responseBody);
            }else {
                personService.deletePerson(personId);
                HashMap<String, Object> responseBody = new HashMap<>();
                responseBody.put("statusCode", HttpStatus.OK.value());
                responseBody.put("message", "Delete person query was successful");
                return ResponseEntity.ok().body(responseBody);
            }
        }catch (Exception ex){
            System.out.println(ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of(
                            "statusCode", HttpStatus.INTERNAL_SERVER_ERROR.value(),
                            "message", "Delete person query internal server error",
                            "error", ex.getMessage()));
        }
    }

    @PutMapping("/{personId}")
    public ResponseEntity<Object> updatePerson(@PathVariable Object personId, @RequestBody Person person) {
        try {
            Optional<Person> findPerson = personService.getPerson(personId);

            if (!findPerson.isPresent()){
                HashMap<String, Object> responseBody = new HashMap<>();
                responseBody.put("statusCode", HttpStatus.NOT_FOUND.value());
                responseBody.put("message", "Update person query was failed. No person found.");
                return ResponseEntity.ok().body(responseBody);
            }else {
                Optional<Person> updatedPerson = personService.updatePerson(personId, person);
                HashMap<String, Object> responseBody = new HashMap<>();
                responseBody.put("data", updatedPerson);
                responseBody.put("statusCode", HttpStatus.OK.value());
                responseBody.put("message", "Update person query was successful");
                return ResponseEntity.ok().body(responseBody);
            }
        }catch (Exception ex){
            System.out.println(ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of(
                            "statusCode", HttpStatus.INTERNAL_SERVER_ERROR.value(),
                            "message", "Update person query internal server error",
                            "error", ex.getMessage()));
        }
    }

}
