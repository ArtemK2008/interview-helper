package com.project.green.dao.impl;

import com.project.green.dao.AnswerDAO;
import com.project.green.entities.Answer;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class AnswerDAOImpl implements AnswerDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public Answer updateAnswer(Answer answer) {
        Session session = entityManager.unwrap(Session.class);

        session.update(answer);

        return answer;
    }

    @Override
    public List<Answer> getAllAnswersToQuestion() {
        Session session = entityManager.unwrap(Session.class);

        String hql = "fROM Answer";
        List<Answer> answers = session.createQuery(hql, Answer.class).getResultList();
     /*   Query query = entityManager.createQuery(hql);
        List<Answer> answers = query.getResultList();*/

        return answers;
    }

    @Override
    public void deleteAnswer(int id) {
        Session session = entityManager.unwrap(Session.class);

        Query query = session.createQuery("delete from Answer where id=: id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public Answer saveAnswer(Answer answer) {
        Session session = entityManager.unwrap(Session.class);

        session.save(answer);

        return answer;
    }

}
