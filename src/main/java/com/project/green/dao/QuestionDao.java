package com.project.green.dao;

import com.project.green.entities.Question;

import java.util.Collection;

public interface QuestionDao {

    void save(Question question);

    Question update(Question question);

    void delete(int id);

    Question getById(int id);

    Collection<Question> getAll();
}
