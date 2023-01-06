package com.project.green.dao.impl;

import com.project.green.entities.Question;
import com.project.green.entities.Statistics;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public class StatisticsDaoImpl implements com.project.green.dao.StatisticsDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Statistics> findAll() {
        return entityManager.createQuery("select s from Statistics as s", Statistics.class).getResultList();
    }

    @Override
    @Transactional
    public Optional<Statistics> getStatisticsById(int id) {
        EntityGraph entityGraph = entityManager.getEntityGraph("statistics-entity-graph");
        return Optional.of(entityManager.createQuery("select s from Statistics s where s.id = :id", Statistics.class)
                .setParameter("id", id)
                .setHint("javax.persistence.fetchgraph", entityGraph)
                .getSingleResult());
    }

    @Override
    @Transactional
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
    @Transactional
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
        if (checkIFQuestionExist(id, question)) {
            return false;
        }
        questionsAnsweredWrong.add(question);
        entityManager.merge(currStatistics);
        return true;
    }

    @Override
    @Transactional
    public boolean removeQuestionFromStatistics(int id, Question question) {
        EntityGraph entityGraph = entityManager.getEntityGraph("statistics-entity-graph");
        Statistics currStatistics = entityManager.createQuery("select s from Statistics s where s.id = :id", Statistics.class)
                .setParameter("id", id)
                .setHint("javax.persistence.fetchgraph", entityGraph)
                .getSingleResult();
        if (currStatistics == null) {
            return false;
        }
        Set<Question> questionsAnsweredWrong = currStatistics.getQuestionsAnsweredWrong();
        if (!checkIFQuestionExist(id, question)) {
            return false;
        }
        questionsAnsweredWrong.remove(question);
        entityManager.merge(currStatistics);
        return true;
    }

    @Override
    public int getCorrectsCount(int id) {
        EntityGraph entityGraph = entityManager.getEntityGraph("statistics-entity-graph");
        Statistics currStatistics = entityManager.createQuery("select s from Statistics s where s.id = :id", Statistics.class)
                .setParameter("id", id)
                .setHint("javax.persistence.fetchgraph", entityGraph)
                .getSingleResult();
        return currStatistics.getCountOfCorrectAnswers();
    }

    @Override
    public int getIncorrectsCount(int id) {
        EntityGraph entityGraph = entityManager.getEntityGraph("statistics-entity-graph");
        Statistics currStatistics = entityManager.createQuery("select s from Statistics s where s.id = :id", Statistics.class)
                .setParameter("id", id)
                .setHint("javax.persistence.fetchgraph", entityGraph)
                .getSingleResult();
        return currStatistics.getCountOfIncorrectAnswers();
    }

    @Override
    @Transactional
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
    @Transactional
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
            return true;
        }
        return false;
    }
}
