package com.project.green.dao;

import com.project.green.entities.Answer;
import com.project.green.entities.Question;

import java.util.List;
import java.util.Optional;

public interface QuestionDao {

    void save(Question question);

    Optional<Question> update(Question question);

    void delete(int id);

    Optional<Question> getById(int id);

    Optional<Question> getByValue(String value);

    Optional<List<Question>> getAll();

    Optional<List<Question>> getAllQuestionsByTopicId(int id);

    Optional<List<String>> countQuestionByTopic();

    void addAnswerToQuestion(int id, Answer answer);
}
