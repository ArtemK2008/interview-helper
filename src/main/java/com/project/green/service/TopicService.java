package com.project.green.service;

import com.project.green.dto.TopicDto;

import java.util.List;

public interface TopicService {

    TopicDto getById(int id);

    List<TopicDto> getAll();

    void save(TopicDto topicDto);

    TopicDto update(TopicDto topicDto);

    void deleteById(int id);

}
