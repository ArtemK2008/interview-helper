package com.project.green.dao.impl;

import com.project.green.dao.QuestionDao;
import com.project.green.dto.CountQuestionDto;
import com.project.green.entities.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class QuestionDaoImpl implements QuestionDao {

    @Autowired
    private EntityManager entityManager;

    @Override
    public void save(Question question) {
        entityManager.persist(question);
    }

    @Override
    public Question update(Question question) {
        return entityManager.merge(question);
    }

    @Override
    public void delete(int id) {
        entityManager.remove(entityManager.getReference(Question.class, id));
    }

    @Override
    public Question getById(int id) {
        return entityManager.find(Question.class, id);
    }

    @Override
    public List<Question> getAll() {
        return entityManager.createQuery("from " + Question.class.getSimpleName(), Question.class).getResultList();
    }

    @Override
    public List<Question> getAllByTopicId(int id) {
        return entityManager.createQuery("select q from Question q where q.topic.id=:id", Question.class)
                .setParameter("id", id).getResultList();
    }

    @Override
    public List<CountQuestionDto> countQuestionByTopic() {
        return entityManager.createNativeQuery("select t.name, count(q) from question q left join topic t on q.topic_id = t.id group by t.name")
                .getResultList();
    }

}
