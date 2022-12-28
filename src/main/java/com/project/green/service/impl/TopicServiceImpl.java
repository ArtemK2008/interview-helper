package com.project.green.service.impl;

import com.project.green.dao.TopicDao;
import com.project.green.entities.Topic;
import com.project.green.exception.EntityNotFoundException;
import com.project.green.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicServiceImpl implements TopicService {

    private final TopicDao topicDao;

    @Autowired
    public TopicServiceImpl(TopicDao topicDao) {
        this.topicDao = topicDao;
    }

    public Topic getTopicById(int id) {
        return topicDao.getTopicById(id).orElseThrow(() -> new EntityNotFoundException("Entity not found."));
    }

    public List<Topic> getAll() {
        return topicDao.getAll();
    }

    public void save(Topic topic) {
        topicDao.save(topic);
    }

    public Topic update(Topic topic) {
        return topicDao.update(topic);
    }

    public void deleteById(int id) {
        topicDao.deleteById(id);
    }
}
