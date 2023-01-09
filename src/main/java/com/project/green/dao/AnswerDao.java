package com.project.green.dao;

import com.project.green.entities.Answer;

import java.util.List;
import java.util.Optional;

public interface AnswerDao {

    Answer updateAnswer(Answer answer);

    List<Answer> getAllAnswers();

    List<Answer> getAllAnswersToQuestionInOrderByVoice(int questionId);

    Optional<Answer> getById(int id);

    Optional<Answer> getByValue(String value);

    Optional<Answer> getByQuestionId(int id);

    void deleteAnswer(int id);

    Answer saveAnswer(Answer answer);

    void incrementVoiceCount(int id, int value);

    int getAnswerVoiceCountById(int id);

}
