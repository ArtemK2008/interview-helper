package com.project.green.dao.impl;

import com.project.green.dao.AnswerDAO;
import com.project.green.entities.Answer;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class AnswerDAOImpl implements AnswerDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Answer updateAnswer(Answer answer) {
        return entityManager.merge(answer);
    }

    @Override
    public List<Answer> getAllAnswersToQuestion() {
        return entityManager.createQuery("fROM Answer", Answer.class).getResultList();
    }

    @Override
    public Answer getById(int id) {
        return entityManager.find(Answer.class, id);
    }


    @Override
    public void deleteAnswer(int id) {
        entityManager.remove(getById(id));
    }

    @Override
    public Answer saveAnswer(Answer answer) {
        entityManager.persist(answer);
        return answer;
    }

}
