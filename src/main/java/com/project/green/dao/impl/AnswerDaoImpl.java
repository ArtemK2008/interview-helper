package com.project.green.dao.impl;

import com.project.green.dao.AnswerDao;
import com.project.green.entities.Answer;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class AnswerDaoImpl implements AnswerDao {

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
    public List<Answer> getAllAnswersToQuestionInOrderByVoice(int id) {
        return entityManager.createQuery("select a from Question q left join Answer a on a.question.id = q.id where q.id=:id order by a.voiceCount desc", Answer.class)
                .setParameter("id", id).getResultList();
    }

    @Override
    public Optional<Answer> getById(int id) {
        EntityGraph entityGraph = entityManager.getEntityGraph("answer-entity-graph");
        return Optional.of(entityManager.createQuery("select a from Answer a where a.id=:id", Answer.class).
                setParameter("id", id).setHint("javax.persistence.fetchgraph", entityGraph)
                .getSingleResult());
    }

    @Override
    public void deleteAnswer(int id) {
        EntityGraph entityGraph = entityManager.getEntityGraph("answer-entity-graph");
        entityManager.createQuery("delete from Answer a where a.id=:id")
                .setParameter("id", id)
                .setHint("javax.persistence.fetchgraph", entityGraph)
                .executeUpdate();
    }

    @Override
    public Answer saveAnswer(Answer answer) {
        entityManager.persist(answer);
        return answer;
    }

}
