package com.project.green.repository.impl;

import com.project.green.entities.Question;
import com.project.green.entities.Statistics;
import com.project.green.repository.StatisticsDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class StatisticsDaoImpl implements StatisticsDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Statistics insert(Statistics statistics) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(statistics);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return statistics;
    }

    @Override
    public boolean deleteById(int id) {
        boolean isDeleted = false;
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from Statistics s where s.personId=:id");
            query.setParameter("id", id);
            Statistics statistics = (Statistics) query.getSingleResult();
            System.out.println(statistics);
            statistics.getPerson().setStatistics(null);
            session.delete(statistics);
            transaction.commit();
            isDeleted = true;
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return isDeleted;
    }

    @Override
    public Statistics getById(int id) {
        Transaction transaction = null;
        Statistics statistics = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            statistics = session.get(Statistics.class, id);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return statistics;
    }

    @Override
    public List<Statistics> findAll() {
        Transaction transaction = null;
        List<Statistics> projects = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Query query = session.createQuery("SELECT s FROM Statistics s",
                    Statistics.class);
            projects = query.getResultList();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return projects;
    }

    @Override
    public int incrementWrongs(int statisticsId) {
        Transaction transaction = null;
        int countAfterIncrement = -1;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from Statistics s where s.personId=:id");
            query.setParameter("id", statisticsId);
            Statistics statistics = (Statistics) query.getSingleResult();
            statistics.setCountOfIncorrectAnswers(statistics.getCountOfIncorrectAnswers() + 1);
            countAfterIncrement = statistics.getCountOfIncorrectAnswers();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return countAfterIncrement;
    }

    @Override
    public int incrementCorrects(int statisticsId) {
        Transaction transaction = null;
        int countAfterIncrement = -1;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from Statistics s where s.personId=:id");
            query.setParameter("id", statisticsId);
            Statistics statistics = (Statistics) query.getSingleResult();
            statistics.setCountOfCorrectAnswers(statistics.getCountOfCorrectAnswers() + 1);
            countAfterIncrement = statistics.getCountOfCorrectAnswers();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return countAfterIncrement;
    }

    @Override
    public boolean addIncorrectQuestion(int statisticsId, Question question) {
        boolean isAdded = false;
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from Statistics s where s.personId=:id");
            query.setParameter("id", statisticsId);
            Statistics statistics = (Statistics) query.getSingleResult();
            statistics.getQuestionsAnsweredWrong().add(question);
            transaction.commit();
            isAdded = true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return  isAdded;
    }
}
