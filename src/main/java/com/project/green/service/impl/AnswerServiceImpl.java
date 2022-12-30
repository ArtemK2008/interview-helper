package com.project.green.service.impl;

import com.project.green.dao.AnswerDAO;
import com.project.green.entities.Answer;
import com.project.green.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AnswerServiceImpl implements AnswerService {


    private AnswerDAO answerDAO;

    @Autowired
    public AnswerServiceImpl(AnswerDAO answerDAO) {
        this.answerDAO = answerDAO;
    }

    @Override
    @Transactional
    public Answer saveAnswer(Answer answer) {
        return answerDAO.saveAnswer(answer);
    }

    @Override
    @Transactional
    public Answer updateAnswer(Answer answer) {
        return answerDAO.updateAnswer(answer);
    }

    @Override
    @Transactional
    public void deleteAnswer(int id) {
        answerDAO.deleteAnswer(id);
    }

    @Override
    @Transactional
    public Answer getById(int id) {
        return answerDAO.getById(id);
    }

    @Override
    @Transactional
    public List<Answer> getAllAnswersToQuestion() {
        return answerDAO.getAllAnswersToQuestion();
    }
}
