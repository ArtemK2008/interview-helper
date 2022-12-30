package com.project.green.service.impl;

import com.project.green.dao.TopicDao;
import com.project.green.dto.TopicDto;
import com.project.green.exception.EntityNotFoundException;
import com.project.green.mapper.TopicMapper;
import com.project.green.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TopicServiceImpl implements TopicService {

    @Autowired
    private TopicDao topicDao;

    @Autowired
    private TopicMapper mapper;

    @Override
    public TopicDto getTopicById(int id) {
        return mapper.toTopicDto(topicDao.getTopicById(id).orElseThrow(() -> new EntityNotFoundException("Entity not found.")));
    }

    @Override
    public List<TopicDto> getAll() {
        return topicDao.getAll().stream().map(mapper::toTopicDto).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void save(TopicDto topicDto) {
        topicDao.save(mapper.toTopic(topicDto));
    }

    @Transactional
    @Override
    public TopicDto update(TopicDto topicDto) {
        return mapper.toTopicDto(topicDao.update(mapper.toTopic(topicDto)));
    }

    @Transactional
    @Override
    public void deleteById(int id) {
        topicDao.deleteById(id);
    }

}
