package com.project.green.service;

import com.project.green.dto.PersonDto;

import java.util.List;

public interface PersonService {

    void save(PersonDto personDto);

    List<PersonDto> getAll();

    PersonDto getById(int id);

    PersonDto update(PersonDto personDto);

    void deleteById(int id);

}
