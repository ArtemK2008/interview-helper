package com.project.green.service;

import com.project.green.entities.Answer;
import com.project.green.entities.Question;

import java.util.List;

public interface AnswerService {

    Answer saveAnswer(Answer answer);

    Answer updateAnswer(Answer answer);

    void deleteAnswer(int id);

    Answer getById(int id);

    List<Answer> getAllAnswersToQuestion();
}
