package com.project.green.dao;

import com.project.green.entities.Person;

import java.util.List;
import java.util.Optional;

public interface PersonDao {

    List<Person> findAll();

    Optional<Person> getPersonById(int id);

    Optional<Person> getPersonByEmail(String email);

    void save(Person person);

    Person update(Person person);

    void deleteById(int id);

}
