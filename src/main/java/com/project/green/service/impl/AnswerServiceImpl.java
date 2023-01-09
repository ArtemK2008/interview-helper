package com.project.green.service.impl;

import com.project.green.dao.AnswerDao;
import com.project.green.dto.AnswerDto;
import com.project.green.entities.Answer;
import com.project.green.exception.EntityNotFoundException;
import com.project.green.exception.NotFoundValueException;
import com.project.green.mapper.AnswerMapper;
import com.project.green.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
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
        return answerMapper.toAnswerDto(answerDAO.getById(id).
                orElseThrow(() -> new NotFoundValueException(Answer.class, "id", id)));
    }

    @Transactional
    @Override
    public AnswerDto getByValue(String value) {
        Optional<Answer> answer = answerDAO.getByValue(value);
        if(answer.isPresent()) {
            return answerMapper.toAnswerDto(answer.get());
        }else{
            throw new EntityNotFoundException("Answer not found");
        }
    }

    @Override
    public AnswerDto getBestByQuestionId(int id) {
        return answerMapper.toAnswerDto(answerDAO.getByQuestionId(id).
                orElseThrow(() -> new NotFoundValueException(Answer.class, "id", id)));
    }


    @Override
    public List<AnswerDto> getAllExistingAnswers() {
        return answerDAO.getAllAnswers().stream().map(answerMapper::toAnswerDto).collect(Collectors.toList());
    }

    @Override
    public List<AnswerDto> getAllAnswersToQuestionInOrderByVoice(int questionId) {
        return answerDAO.getAllAnswersToQuestionInOrderByVoice(questionId).stream().map(answerMapper::toAnswerDto).collect(Collectors.toList());
    }

    @Override
    public void incrementVoiceCount(int id, int value) {
        answerDAO.incrementVoiceCount(id,1);
    }

    @Override
    public int getAnswerVoiceCountById(int id) {
        return answerDAO.getAnswerVoiceCountById(id);
    }

}
