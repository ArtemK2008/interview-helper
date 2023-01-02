package com.project.green.service;

import com.project.green.dto.AnswerDto;
import com.project.green.entities.Answer;

import java.util.List;

public interface AnswerService {

    AnswerDto saveAnswer(AnswerDto answerDto);

    AnswerDto updateAnswer(AnswerDto answerDto);

    void deleteAnswer(int id);

    AnswerDto getById(int id);

    List<AnswerDto> getAllAnswersToQuestion();

}
