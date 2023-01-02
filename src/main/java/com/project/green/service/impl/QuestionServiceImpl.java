package com.project.green.service.impl;

import com.project.green.dao.QuestionDao;
import com.project.green.dto.CountQuestionDto;
import com.project.green.dto.QuestionDto;
import com.project.green.mapper.QuestionMapper;
import com.project.green.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionDao questionDao;

    @Autowired
    private QuestionMapper questionMapper;

    @Transactional
    @Override
    public void save(QuestionDto questionDto) {
        if (questionDto == null) {
            throw new IllegalArgumentException("Question is null");
        }
        questionDao.save(questionMapper.toQuestionEntity(questionDto));
    }

    @Transactional
    @Override
    public QuestionDto update(QuestionDto questionDto) {
        if (questionDto == null) {
            throw new IllegalArgumentException("Question is null");
        }
        return questionMapper.toQuestionDto(questionDao.update(questionMapper.toQuestionEntity(questionDto)));
    }

    @Override
    public List<QuestionDto> getAll() {
        return questionDao.getAll().stream().map(questionMapper::toQuestionDto).collect(Collectors.toList());
    }

    @Override
    public QuestionDto getById(int id) {
        return questionMapper.toQuestionDto(questionDao.getById(id));
    }

    @Transactional
    @Override
    public void deleteById(int id) {
        questionDao.delete(id);
    }

    @Override
    public List<QuestionDto> findAllByTopicId(int id) {
        return questionDao.getAllByTopicId(id).stream().map(questionMapper::toQuestionDto).collect(Collectors.toList());
    }

    @Override
    public List<CountQuestionDto> countQuestionsByTopic() {
        return questionDao.countQuestionByTopic();
    }

}
