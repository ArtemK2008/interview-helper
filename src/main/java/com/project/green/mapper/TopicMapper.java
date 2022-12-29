package com.project.green.mapper;

import com.project.green.dto.TopicDto;
import com.project.green.entities.Topic;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TopicMapper {

    TopicDto toTopicDto(Topic topic);

    Topic toTopic(TopicDto topicDto);
}
