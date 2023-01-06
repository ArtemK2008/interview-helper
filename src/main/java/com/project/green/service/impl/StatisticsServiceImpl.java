package com.project.green.service.impl;

import com.project.green.dao.StatisticsDao;
import com.project.green.dto.QuestionDto;
import com.project.green.dto.StatisticsDto;
import com.project.green.entities.Person;
import com.project.green.entities.Question;
import com.project.green.exception.NotFoundValueException;
import com.project.green.mapper.QuestionMapper;
import com.project.green.mapper.StatisticsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StatisticsServiceImpl implements com.project.green.service.StatisticsService {
    @Autowired
    private StatisticsDao statisticsDao;

    @Autowired
    private StatisticsMapper statisticsMapper;

    @Autowired
    QuestionMapper questionMapper;

    @Override
    public StatisticsDto getById(int id) {
        return statisticsMapper.toStatisticsDto(statisticsDao.getStatisticsById(id).
                orElseThrow(() -> new NotFoundValueException(Person.class, "id", id)));
    }

    @Override
    public List<StatisticsDto> getAll() {
        return statisticsDao.findAll().stream().map(statisticsMapper::toStatisticsDto).collect(Collectors.toList());
    }

    @Override
    public List<QuestionDto> getUnansweredQuestionsById(int id) {
        return statisticsDao.getUnansweredQuestionsById(id).stream().map(questionMapper::toQuestionDto).collect(Collectors.toList());
    }

    @Override
    public void addQuestionToStatistics(int id, Question question) {
        statisticsDao.addQuestionToStatistics(id, question);
    }

    @Override
    public void removeQuestionFromStatistics(int id, Question question) {
        statisticsDao.removeQuestionFromStatistics(id, question);
    }

    @Override
    public int getCorrectCount(int id) {
        return statisticsDao.getCorrectsCount(id);
    }

    @Override
    public int getIncorrectCount(int id) {
        return statisticsDao.getIncorrectsCount(id);
    }

    @Override
    public void incrementCorrects(int id, int value) {
        statisticsDao.incrementCorrects(id, value);
    }

    @Override
    public void incrementIncorrect(int id, int value) {
        statisticsDao.incrementIncorrects(id, value);
    }


}
