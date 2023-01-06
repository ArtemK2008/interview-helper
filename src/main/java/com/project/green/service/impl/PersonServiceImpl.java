package com.project.green.service.impl;

import com.project.green.dao.PersonDao;
import com.project.green.dao.TopicDao;
import com.project.green.dto.PersonDto;
import com.project.green.entities.Person;
import com.project.green.exception.NotFoundValueException;
import com.project.green.mapper.PersonMapper;
import com.project.green.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonDao personDao;

    @Autowired
    private TopicDao topicDao;

    @Autowired
    private PersonMapper mapper;

    @Transactional
    @Override
    public void save(PersonDto personDto) {
        Person person = mapper.toPerson(personDto);
        personDao.save(person);
    }

    @Override
    public List<PersonDto> getAll() {
        return personDao.findAll().stream().map(mapper::toPersonDto).collect(Collectors.toList());
    }

    @Override
    public PersonDto getById(int id) {
        return mapper.toPersonDto(personDao.getPersonById(id).
                orElseThrow(() -> new NotFoundValueException(Person.class, "id", id)));
    }

    @Override
    public PersonDto getByEmail(String email) {
        return mapper.toPersonDto(personDao.getPersonByEmail(email)
                .orElseThrow(() -> new NotFoundValueException(Person.class, "email", email)));
    }


    @Transactional
    @Override
    public PersonDto update(PersonDto personDto) {
        return mapper.toPersonDto(personDao.update(mapper.toPerson(personDto)));
    }

    @Transactional
    @Override
    public void deleteById(int id) {
        personDao.deleteById(id);
    }
}
