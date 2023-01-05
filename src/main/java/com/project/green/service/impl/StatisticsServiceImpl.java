package com.project.green.service.impl;

import com.project.green.dao.StatisticsDao;
import com.project.green.dto.QuestionDto;
import com.project.green.dto.StatisticsDto;
import com.project.green.mapper.QuestionMapper;
import com.project.green.mapper.StatisticsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatisticsServiceImpl {
    @Autowired
    private StatisticsDao statisticsDao;

    @Autowired
    private StatisticsMapper statisticsMapper;

    public StatisticsDto getById(int id) { return statisticsMapper.toStatisticsDto(statisticsDao.getStatisticsById(id)); }


}
