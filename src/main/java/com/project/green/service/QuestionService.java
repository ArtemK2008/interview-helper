package com.project.green.service;

import com.project.green.dto.QuestionDto;
import com.project.green.dto.TopicDto;

import java.util.List;

public interface QuestionService {

    void save(QuestionDto questionDto);

    QuestionDto update(QuestionDto questionDto);

    List<QuestionDto> getAll();

    QuestionDto getById(int id);

    QuestionDto getByValue(String value);

    void deleteById(int id);

    List<QuestionDto> findAllByTopicId(int id);

    List<TopicDto> countQuestionsByTopic();

    boolean addAnswerToQuestion(int id, String answerText);

}
