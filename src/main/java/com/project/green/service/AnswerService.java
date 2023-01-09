package com.project.green.service;

import com.project.green.dto.AnswerDto;

import java.util.List;

public interface AnswerService {

    AnswerDto saveAnswer(AnswerDto answerDto);

    AnswerDto updateAnswer(AnswerDto answerDto);

    void deleteAnswer(int id);

    AnswerDto getById(int id);

    public AnswerDto getByValue(String value);

    AnswerDto getBestByQuestionId(int id);

    List<AnswerDto> getAllExistingAnswers();

    List<AnswerDto> getAllAnswersToQuestionInOrderByVoice(int questionId);

    void incrementVoiceCount(int id, int value);

    int getAnswerVoiceCountById(int id);
}
