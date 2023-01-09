package com.project.green.dao.impl;

import com.project.green.dao.AnswerDao;
import com.project.green.entities.Answer;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
    public List<Answer> getAllAnswers() {
        EntityGraph entityGraph = entityManager.getEntityGraph("answer-entity-graph");
        return entityManager.createQuery("fROM Answer", Answer.class)
                .setHint("javax.persistence.fetchgraph", entityGraph).getResultList();
    }

    @Override
    public List<Answer> getAllAnswersToQuestionInOrderByVoice(int id) {
        EntityGraph entityGraph = entityManager.getEntityGraph("answer-entity-graph");
        return entityManager.createQuery("select a from Question q left join Answer a on a.question.id = q.id where q.id=:id order by a.voiceCount desc", Answer.class)
                .setHint("javax.persistence.fetchgraph", entityGraph)
                .setParameter("id", id).getResultList();
    }

    @Override
    public Optional<Answer> getById(int id) {
        EntityGraph entityGraph = entityManager.getEntityGraph("answer-entity-graph");
        return Optional.of(entityManager.createQuery("select a from Answer a where a.id=:id", Answer.class).
                setParameter("id", id).setHint("javax.persistence.fetchgraph", entityGraph)
                .getSingleResult());
    }

    public Optional<Answer> getByValue(String value) {
        EntityGraph entityGraph = entityManager.getEntityGraph("answer-entity-graph");
        return Optional.ofNullable(entityManager.createQuery("select a from Answer a where a.answerText = :value", Answer.class).
                setParameter("value", value).setHint("javax.persistence.fetchgraph", entityGraph)
                .getSingleResult());
    }

    public Optional<Answer> getByQuestionId(int id) {
        EntityGraph entityGraph = entityManager.getEntityGraph("answer-entity-graph");
        return Optional.of(entityManager.createQuery("select a from Answer a where a.question.id=:id and isDefault=true", Answer.class).
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

    @Override
    public int getAnswerVoiceCountById(int id) {
        return getById(id).get().getVoiceCount();
    }

    @Override
    @Transactional
    public void incrementVoiceCount(int id, int value) {
        Optional<Answer> answer = getById(id);
        int beforeCount = answer.get().getVoiceCount();
        answer.get().setVoiceCount(beforeCount+value);
        entityManager.merge(answer.get());
    }

    @Transactional
    public boolean checkIfAnswersVoiceCountBiggerThenDefault(int questionId,Answer answer) {
        EntityGraph entityGraph = entityManager.getEntityGraph("answer-entity-graph");
        Optional<Answer> defaulAnswer = Optional.of(entityManager.createQuery("select a from Answer a where a.question.id=:id " +
                        "and a.isDefault=:bool ", Answer.class).setParameter("id", questionId).
                setParameter("bool", true).setHint("javax.persistence.fetchgraph", entityGraph)
                .getSingleResult());
        return answer.getVoiceCount() > defaulAnswer.get().getVoiceCount();
    }

    @Transactional
    public void swapDefaultForNewOne(int questionId,Answer answer) {
        EntityGraph entityGraph = entityManager.getEntityGraph("answer-entity-graph");
        Optional<Answer> defaulAnswer = Optional.of(entityManager.createQuery("select a from Answer a where a.question.id=:id " +
                        "and a.isDefault=:bool ", Answer.class).setParameter("id", questionId).
                setParameter("bool", true).setHint("javax.persistence.fetchgraph", entityGraph)
                .getSingleResult());
        defaulAnswer.get().setDefault(false);
        answer.setDefault(true);
        entityManager.merge(answer);
    }

}
