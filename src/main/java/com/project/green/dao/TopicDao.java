package com.project.green.dao;

import com.project.green.entities.Topic;

import java.util.List;
import java.util.Optional;

public interface TopicDao {

    List<Topic> getAll();

    Optional<Topic> getTopicById(int id);

    void save(Topic topic);

    Topic update(Topic topic);

    void deleteById(int id);

}
