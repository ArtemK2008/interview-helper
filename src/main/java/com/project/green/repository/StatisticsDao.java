package com.project.green.repository;

import com.project.green.entities.Question;
import com.project.green.entities.Statistics;

import java.util.List;

public interface StatisticsDao {
    Statistics insert(Statistics statistics);

    boolean deleteById(int id);

    Statistics getById(int id);

    List<Statistics> findAll();

    int incrementWrongs(int statisticsId);

    int incrementCorrects(int statisticsId);

    boolean addIncorrectQuestion(int statisticsId, Question question);
}
