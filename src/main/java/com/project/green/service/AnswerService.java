package com.project.green.service;

import com.project.green.dto.AnswerDto;

import java.util.List;

public interface AnswerService {

    AnswerDto saveAnswer(AnswerDto answerDto);

    AnswerDto updateAnswer(AnswerDto answerDto);

    void deleteAnswer(int id);

    AnswerDto getById(int id);

    AnswerDto getBestByQuestionId(int id);

    List<AnswerDto> getAllAnswersToQuestion();

    List<AnswerDto> getAllAnswersToQuestionInOrderByVoice(int questionId);
}
