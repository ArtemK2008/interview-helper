package com.project.green.dao.impl;

import com.project.green.dao.PersonDao;
import com.project.green.entities.Person;
import com.project.green.entities.Question;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public class PersonDaoImpl implements PersonDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Person> findAll() {
        EntityGraph entityGraph = entityManager.getEntityGraph("person-entity-graph");
        return entityManager.createQuery("select p from Person as p", Person.class)
                .setHint("javax.persistence.fetchgraph", entityGraph).getResultList();
    }

    @Override
    public Optional<Person> getPersonById(int id) {
        EntityGraph entityGraph = entityManager.getEntityGraph("person-entity-graph");
        return Optional.of(entityManager.createQuery("select p from Person p where p.id=:id", Person.class).
                setParameter("id", id).setHint("javax.persistence.fetchgraph", entityGraph)
                .getSingleResult());
    }


    @Override
    public Optional<Person> getPersonByEmail(String email) {
        EntityGraph entityGraph = entityManager.getEntityGraph("person-entity-graph");
        Person currPerson = entityManager.createQuery("select p from Person p where p.email=:email", Person.class)
                .setParameter("email", email)
                .setHint("javax.persistence.fetchgraph", entityGraph)
                .getSingleResult();

        return Optional.of(currPerson);
    }


    @Override
    public void save(Person person) {
        entityManager.persist(person);
    }

    @Override
    public Person update(Person person) {
        return entityManager.merge(person);
    }

    @Override
    public void deleteById(int id) {
        EntityGraph entityGraph = entityManager.getEntityGraph("person-entity-graph");
        entityManager.createQuery("delete from Person p where p.id=:id")
                .setParameter("id", id)
                .setHint("javax.persistence.fetchgraph", entityGraph)
                .executeUpdate();
    }

    @Override
    @Transactional
    public Set<Question> getSavedQuestionsById(int id) {
        return getPersonById(id).get().getSavedQuestions();
    }

    @Override
    @Transactional
    public boolean addQuestionToFavourites(int id, Question question) {
        Person currPerson = getPersonById(id).get();
        Set<Question> questionsSavedAsFavourites = currPerson.getSavedQuestions();
        if (!checkIFQuestionExist(id, question)) {
            questionsSavedAsFavourites.add(question);
            List<Person> personsWhoSavedThis;
            personsWhoSavedThis = question.getPersonsWhoSavedThis();
            if(personsWhoSavedThis == null) {
                personsWhoSavedThis = new ArrayList<>();
            }
            personsWhoSavedThis.add(currPerson);
            question.setPersonsWhoSavedThis(personsWhoSavedThis);
            currPerson.setSavedQuestions(questionsSavedAsFavourites);
            entityManager.merge(question);
            entityManager.merge(currPerson);
            return  true;
        }
        return false;
    }

    private boolean checkIFQuestionExist(int id, Question question) {
        Set<Question> questionsSavedAsFavourites = getSavedQuestionsById(id);
        return questionsSavedAsFavourites.contains(question);
    }
}
