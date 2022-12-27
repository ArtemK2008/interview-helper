package com.project.green.service;

import com.project.green.dao.QuestionDao;
import com.project.green.dao.impl.QuestionDaoImpl;
import com.project.green.entities.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
public class TransactionalQuestionService {

    @Autowired
    private QuestionDao questionRepository;

    @Transactional
    public void createQuestion(final Question question){
        if(question == null){
            throw new IllegalArgumentException("Question is null");
        }
        questionRepository.save(question);
    }

    @Transactional
    public Question updateQuestions(final Question question){
        if(question == null){
            throw new IllegalArgumentException("Question is null");
        }
        return questionRepository.update(question);
    }

    @Transactional
    public Collection<Question> findAll(){
        return questionRepository.getAll();
    }

    @Transactional
    public Question findById(int id){
        return questionRepository.getById(id);
    }

    @Transactional
    public void removeById(int id){
        questionRepository.delete(id);
    }
}
