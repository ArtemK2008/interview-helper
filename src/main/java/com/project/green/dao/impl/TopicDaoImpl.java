package com.project.green.dao.impl;

import com.project.green.dao.TopicDao;
import com.project.green.entities.Topic;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class TopicDaoImpl implements TopicDao {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Topic> getAll() {
        return entityManager.createQuery("select t from Topic as t", Topic.class).getResultList();
    }

    public Optional<Topic> getTopicById(int id) {
        return Optional.of(entityManager.createQuery("select t from Topic t where t.id=:id", Topic.class).
                setParameter("id", id).
                getSingleResult());
    }

    public void save(Topic topic) {
        entityManager.persist(topic);
    }
    public Topic update(Topic topic) {
        return entityManager.merge(topic);
    }

    public void deleteById(int id) {
        entityManager.createQuery("delete from Topic t where t.id=:id")
                .setParameter("id", id)
                .executeUpdate();
    }

}
