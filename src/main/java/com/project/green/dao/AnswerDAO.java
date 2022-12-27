package com.project.green.dao;

import com.project.green.entities.Answer;

import java.util.List;

public interface AnswerDAO {

    Answer updateAnswer(Answer answer);

    List<Answer> getAllAnswersToQuestion();

    Answer getById(int id);

    void deleteAnswer(int id);

    void saveAnswer(Answer answer);
}
