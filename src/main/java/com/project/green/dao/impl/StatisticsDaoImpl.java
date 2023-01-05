package com.project.green.dao.impl;

import com.project.green.entities.Question;
import com.project.green.entities.Statistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.Set;

@Repository
public class StatisticsDaoImpl implements com.project.green.dao.StatisticsDao {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Statistics> findAll() {
        return entityManager.createQuery("select s from Statistics as s", Statistics.class).getResultList();
    }

    @Override
    public Statistics getStatisticsById(int id) {
        EntityGraph entityGraph = entityManager.getEntityGraph("statistics-entity-graph");
        Statistics currStatistics = entityManager.createQuery("select s from Statistics s where s.id = :id", Statistics.class)
                .setParameter("id", id)
                .setHint("javax.persistence.fetchgraph", entityGraph)
                .getSingleResult();
        return currStatistics;
    }

    @Override
    public Set<Question> getUnansweredQuestionsById(int id) {
        EntityGraph entityGraph = entityManager.getEntityGraph("statistics-entity-graph");
        Statistics currStatistics = entityManager.createQuery("select s from Statistics s where s.id = :id", Statistics.class)
                .setParameter("id", id)
                .setHint("javax.persistence.fetchgraph", entityGraph)
                .getSingleResult();
        if (currStatistics == null) {
            return null;
        }
        return currStatistics.getQuestionsAnsweredWrong();
    }

    @Override
    public boolean addQuestionToStatistics(int id, Question question) {
        EntityGraph entityGraph = entityManager.getEntityGraph("statistics-entity-graph");
        Statistics currStatistics = entityManager.createQuery("select s from Statistics s where s.id = :id", Statistics.class)
                .setParameter("id", id)
                .setHint("javax.persistence.fetchgraph", entityGraph)
                .getSingleResult();
        if (currStatistics == null) {
            return false;
        }
        Set<Question> questionsAnsweredWrong = currStatistics.getQuestionsAnsweredWrong();
        questionsAnsweredWrong.add(question);
        entityManager.merge(questionsAnsweredWrong);
        if (checkIFQuestionExist(id, question)) {
            return false;
        }
        return true;
    }

    @Override
    public void incrementCorrects(int id, int value) {
        EntityGraph entityGraph = entityManager.getEntityGraph("statistics-entity-graph");
        Statistics currStatistics = entityManager.createQuery("select s from Statistics s where s.id = :id", Statistics.class)
                .setParameter("id", id)
                .setHint("javax.persistence.fetchgraph", entityGraph)
                .getSingleResult();
        int beforeCount = currStatistics.getCountOfCorrectAnswers();
        currStatistics.setCountOfCorrectAnswers(beforeCount + value);
        entityManager.merge(currStatistics);
    }

    @Override
    public void incrementIncorrects(int id, int value) {
        EntityGraph entityGraph = entityManager.getEntityGraph("statistics-entity-graph");
        Statistics currStatistics = entityManager.createQuery("select s from Statistics s where s.id = :id", Statistics.class)
                .setParameter("id", id)
                .setHint("javax.persistence.fetchgraph", entityGraph)
                .getSingleResult();
        int beforeCount = currStatistics.getCountOfIncorrectAnswers();
        currStatistics.setCountOfIncorrectAnswers(beforeCount + value);
        entityManager.merge(currStatistics);
    }

    private boolean checkIFQuestionExist(int id, Question question) {
        Set<Question> unansweredQuestionsById = getUnansweredQuestionsById(id);
        if (unansweredQuestionsById.contains(question)) {
            return false;
        }
        return true;
    }
}
