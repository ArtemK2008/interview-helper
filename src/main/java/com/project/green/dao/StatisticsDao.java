package com.project.green.dao;

import com.project.green.entities.Question;
import com.project.green.entities.Statistics;

import java.util.List;
import java.util.Set;

public interface StatisticsDao {
    List<Statistics> findAll();

    Statistics getStatisticsById(int id);

    Set<Question> getUnansweredQuestionsById(int id);

    int getIncorrectsCount(int id);

    int getCorrectsCount(int id);

    boolean addQuestionToStatistics(int id, Question question);

    boolean removeQuestionFromStatistics(int id, Question question);

    void incrementCorrects(int id, int value);

    void incrementIncorrects(int id, int value);
}
