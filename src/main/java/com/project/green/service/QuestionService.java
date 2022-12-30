package com.project.green.service;

import com.project.green.dto.CountQuestionDto;
import com.project.green.dto.QuestionDto;

import java.util.List;

public interface QuestionService {

    void createQuestion(QuestionDto questionDto);

    QuestionDto updateQuestions(QuestionDto questionDto);

    List<QuestionDto> findAll();

    QuestionDto findById(int id);

    void deleteById(int id);

    List<QuestionDto> findAllByTopicId(int id);

    List<CountQuestionDto> countQuestionsByTopic();

}
