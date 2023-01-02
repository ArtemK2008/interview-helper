package com.project.green.dao.impl;

import com.project.green.dao.PersonDao;
import com.project.green.entities.Person;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class PersonDaoImpl implements PersonDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Person> findAll() {
        return entityManager.createQuery("select p from Person as p", Person.class).getResultList();
    }

    @Override
    public Optional<Person> getPersonById(int id) {
        return Optional.of(entityManager.createQuery("select p from Person p where p.id=:id", Person.class).
                setParameter("id", id).
                getSingleResult());
    }

    @Override
    public Optional<Person> getPersonByEmail(String email) {
        return Optional.of(entityManager.createQuery("select p from Person p where p.email=:email", Person.class).
                setParameter("email", email).
                getSingleResult());
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
        entityManager.createQuery("delete from Person p where p.id=:id")
                .setParameter("id", id)
                .executeUpdate();
    }

}
