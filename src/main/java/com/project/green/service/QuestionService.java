package com.project.green.service;

import com.project.green.dto.CountQuestionDto;
import com.project.green.dto.QuestionDto;

import java.util.List;

public interface QuestionService {

    void save(QuestionDto questionDto);

    QuestionDto update(QuestionDto questionDto);

    List<QuestionDto> getAll();

    QuestionDto getById(int id);

    void deleteById(int id);

    List<QuestionDto> findAllByTopicId(int id);

    List<CountQuestionDto> countQuestionsByTopic();

}
