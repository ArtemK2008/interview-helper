package com.project.green.dao;

import com.project.green.entities.Answer;

import java.util.List;
import java.util.Optional;

public interface AnswerDao {

    Answer updateAnswer(Answer answer);

    List<Answer> getAllAnswersToQuestion();

    List<Answer> getAllAnswersToQuestionInOrderByVoice(int questionId);

    Optional<Answer> getById(int id);

    void deleteAnswer(int id);

    Answer saveAnswer(Answer answer);

}
