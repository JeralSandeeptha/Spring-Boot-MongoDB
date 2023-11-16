package com.example.mongodbpractice.repository;

import com.example.mongodbpractice.model.Person;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepo extends MongoRepository<Person, Object> {
}
