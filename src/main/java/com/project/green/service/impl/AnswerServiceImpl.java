package com.project.green.service.impl;

import com.project.green.dao.AnswerDao;
import com.project.green.dto.AnswerDto;
import com.project.green.entities.Answer;
import com.project.green.mapper.AnswerMapper;
import com.project.green.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnswerServiceImpl implements AnswerService {

    @Autowired
    private AnswerDao answerDAO;

    @Autowired
    private AnswerMapper answerMapper;

    @Override
    @Transactional
    public AnswerDto saveAnswer(AnswerDto answerDto) {
        if (answerDto == null) {
            throw new IllegalArgumentException("Answer is null");
        }
        return answerMapper.toAnswerDto(answerDAO.saveAnswer(answerMapper.toAnswer(answerDto)));
    }

    @Override
    @Transactional
    public AnswerDto updateAnswer(AnswerDto answerDto) {
        if (answerDto == null) {
            throw new IllegalArgumentException("Answer is null");
        }
        return answerMapper.toAnswerDto(answerDAO.updateAnswer(answerMapper.toAnswer(answerDto)));
    }

    @Override
    @Transactional
    public void deleteAnswer(int id) {
        answerDAO.deleteAnswer(id);
    }

    @Override
    public AnswerDto getById(int id) {
        Answer answer = answerDAO.getById(id).orElse(null);
        return answerMapper.toAnswerDto(answer);
    }

    @Override
    public List<AnswerDto> getAllAnswersToQuestion() {
        return answerDAO.getAllAnswersToQuestion().stream().map(answerMapper::toAnswerDto).collect(Collectors.toList());
    }

    @Override
    public List<AnswerDto> getAllAnswersToQuestionInOrderByVoice(int questionId) {
        return answerDAO.getAllAnswersToQuestionInOrderByVoice(questionId).stream().map(answerMapper::toAnswerDto).collect(Collectors.toList());
    }
}
