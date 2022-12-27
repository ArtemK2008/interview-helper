package com.project.green.repository.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class DatabaseCleaner {
    @Autowired
    private SessionFactory sessionFactory;

    public void clearAllTables() {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session
                    .createNativeQuery("ALTER SEQUENCE answer_id_seq RESTART WITH 1")
                    .executeUpdate();
            session
                    .createNativeQuery("ALTER SEQUENCE person_id_seq RESTART WITH 1")
                    .executeUpdate();
            session
                    .createNativeQuery("ALTER SEQUENCE question_id_seq RESTART WITH 1")
                    .executeUpdate();
            session
                    .createNativeQuery("ALTER SEQUENCE roles_id_seq RESTART WITH 1")
                    .executeUpdate();
            session
                    .createNativeQuery("ALTER SEQUENCE statistics_person_id_seq RESTART WITH 1")
                    .executeUpdate();
            session
                    .createNativeQuery("ALTER SEQUENCE topic_id_seq RESTART WITH 1")
                    .executeUpdate();


            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }
}