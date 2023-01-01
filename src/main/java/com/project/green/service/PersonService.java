package com.project.green.service;

import com.project.green.entities.Person;

import java.util.List;

public interface PersonService {

    Person save(Person person);

    List<Person> findAll();

    Person findById(int id);

    Person update(Person person);

    Person delete(int id);

}
