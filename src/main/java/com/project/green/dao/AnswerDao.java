package com.project.green.dao;

import com.project.green.entities.Answer;

import java.util.List;

public interface AnswerDao {

    Answer updateAnswer(Answer answer);

    List<Answer> getAllAnswersToQuestion();

    List<Answer> getAllAnswersToQuestionInOrderByVoice(int questionId);

    Answer getById(int id);

    void deleteAnswer(int id);

    Answer saveAnswer(Answer answer);

}
