package com.project.green.dao;

import com.project.green.entities.Answer;

import java.util.List;

public interface AnswerDAO {

    Answer updateAnswer(Answer answer);

    List<Answer> getAllAnswersToQuestion();

    void deleteAnswer(int id);

    Answer saveAnswer(Answer answer);
}
