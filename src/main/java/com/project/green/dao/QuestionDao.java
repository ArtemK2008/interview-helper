package com.project.green.dao;

import com.project.green.dto.CountQuestionDto;
import com.project.green.entities.Question;

import java.util.List;

public interface QuestionDao {

    void save(Question question);

    Question update(Question question);

    void delete(int id);

    Question getById(int id);

    List<Question> getAll();

    List<Question> getAllByTopicId(int id);

    List<CountQuestionDto> countQuestionByTopic();

}
