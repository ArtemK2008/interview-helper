package com.project.green.service;

import com.project.green.dto.PersonDto;
import com.project.green.dto.QuestionDto;

import java.util.List;

public interface PersonService {

    void save(PersonDto personDto);

    List<PersonDto> getAll();

    PersonDto getById(int id);

    PersonDto getByEmail(String email);

    PersonDto update(PersonDto personDto);

    void deleteById(int id);

    List<QuestionDto> getSavedQuestionsById(int id);

    void addQuestionToFavourites(int id, QuestionDto questionDto);

}
