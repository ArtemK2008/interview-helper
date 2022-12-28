package com.project.green.service;

import com.project.green.entities.Topic;

import java.util.List;

public interface TopicService {

    Topic getTopicById(int id);

    List<Topic> getAll();

    void save(Topic topic);

    Topic update(Topic topic);

    void deleteById(int id);

}
