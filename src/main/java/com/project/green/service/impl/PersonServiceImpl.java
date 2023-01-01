package com.project.green.service.impl;

import com.project.green.entities.Person;
import com.project.green.exeption.NotFoundValueException;
import com.project.green.repository.impl.PersonRepository;
import com.project.green.service.PersonService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public Person save(Person person) {
        return personRepository.save(person);
    }

    @Override
    public List<Person> findAll() {
        return personRepository.findAll();
    }

    @Override
    public Person findById(int id) {
        return personRepository.findById(id).
                orElseThrow(() -> new NotFoundValueException(Person.class, "id", id));
    }

    @Override
    public Person update(Person person) {
        int id = person.getId();
        findById(id);
        return save(person);
    }

    @Override
    public Person delete(int id) {
        Person person = findById(id);
        personRepository.deleteById(id);
        return person;
    }
}
