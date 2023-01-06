package com.project.green.dao.impl;

import com.project.green.dao.QuestionDao;
import com.project.green.entities.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public class QuestionDaoImpl implements QuestionDao {

    @Autowired
    private EntityManager entityManager;

    @Override
    public void save(Question question) {
        entityManager.persist(question);
    }

    @Override
    public Optional<Question> update(Question question) {
        return Optional.of(entityManager.merge(question));
    }

    @Override
    public void delete(int id) {
        EntityGraph entityGraph = entityManager.getEntityGraph("question-entity-graph");
        entityManager.createQuery("delete from Question q where q.id=:id")
                .setParameter("id", id)
                .setHint("javax.persistence.fetchgraph", entityGraph)
                .executeUpdate();
    }

    @Override
    public Optional<Question> getById(int id) {
        EntityGraph entityGraph = entityManager.getEntityGraph("question-entity-graph");
        return Optional.of(entityManager.createQuery("select q from Question q where q.id=:id", Question.class).
                setParameter("id", id).setHint("javax.persistence.fetchgraph", entityGraph)
                .getSingleResult());
    }

    public Optional<Question> getByValue(String value) {
        EntityGraph entityGraph = entityManager.getEntityGraph("question-entity-graph");
        return Optional.ofNullable(entityManager.createQuery("select q from Question q where q.questionText = ?1", Question.class).
                setParameter(1, value).setHint("javax.persistence.fetchgraph", entityGraph)
                .getSingleResult());
    }

    @Override
    public Optional<List<Question>> getAll() {
        EntityGraph entityGraph = entityManager.getEntityGraph("person-entity-graph");
        return Optional.ofNullable(entityManager.createQuery("from " + Question.class.getSimpleName(), Question.class)
                .setHint("javax.persistence.fetchgraph", entityGraph).getResultList());
    }

    @Override
    public Optional<List<Question>> getAllQuestionsByTopicId(int id) {
        EntityGraph entityGraph = entityManager.getEntityGraph("person-entity-graph");
        return Optional.ofNullable(entityManager.createQuery("select q from Question q where q.topic.id=:id", Question.class)
                .setParameter("id", id).setHint("javax.persistence.fetchgraph", entityGraph).getResultList());
    }

    @Override
    public Optional<List<String>> countQuestionByTopic() {
        EntityGraph entityGraph = entityManager.getEntityGraph("person-entity-graph");
        return Optional.ofNullable(entityManager.createNativeQuery(
                        "select t.name, count(q) from question q left join topic t on q.topic_id = t.id group by t.name", String.class)
                .setHint("javax.persistence.fetchgraph", entityGraph)
                .getResultList());
    }

}
