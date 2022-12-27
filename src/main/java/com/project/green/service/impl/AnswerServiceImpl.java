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

    @Autowired
    private AnswerDAO answerDAO;

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
    public Answer getById(int id) {
        return null;
    }

    @Override
    @Transactional
    public List<Answer> getAllAnswersToQuestion() {
        return answerDAO.getAllAnswersToQuestion();
    }
}
