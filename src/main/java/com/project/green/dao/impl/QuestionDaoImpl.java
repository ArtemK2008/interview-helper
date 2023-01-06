package com.project.green.dao.impl;

import com.project.green.dao.QuestionDao;
import com.project.green.entities.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
        entityManager.remove(entityManager.getReference(Question.class, id));
    }

    @Override
    public Optional<Question> getById(int id) {
        return Optional.of(entityManager.find(Question.class, id));
    }

    public Optional<Question> getByValue(String value) {
        return Optional.ofNullable(entityManager.createQuery("select q from Question q where q.questionText = ?1", Question.class)
                .setParameter(1, value)
                .getSingleResult());
    }

    @Override
    public Optional<List<Question>> getAll() {
        return Optional.ofNullable(entityManager.createQuery("from " + Question.class.getSimpleName(), Question.class).getResultList());
    }

    @Override
    public Optional<List<Question>> getAllByTopicId(int id) {
        return Optional.ofNullable(entityManager.createQuery("select q from Question q where q.topic.id=:id", Question.class)
                .setParameter("id", id).getResultList());
    }

    @Override
    public Optional<List<String>> countQuestionByTopic() {
        return Optional.ofNullable(entityManager.createNativeQuery(
                        "select t.name, count(q) from question q left join topic t on q.topic_id = t.id group by t.name", String.class)
                .getResultList());
    }

}
