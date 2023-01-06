package com.project.green.service;

import com.project.green.dto.QuestionDto;
import com.project.green.dto.StatisticsDto;
import com.project.green.entities.Question;

import java.util.List;

public interface StatisticsService {
    StatisticsDto getById(int id);

    List<StatisticsDto> getAll();

    List<QuestionDto> getUnansweredQuestionsById(int id);

    void addQuestionToStatistics(int id, QuestionDto questionDto);

    void removeQuestionFromStatistics(int id, QuestionDto questionDto);

    int getCorrectCount(int id);

    int getIncorrectCount(int id);

    void incrementCorrects(int id, int value);

    void incrementIncorrect(int id, int value);
}
