package com.project.green.mapper;

import com.project.green.dto.QuestionDto;
import com.project.green.entities.Question;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {TopicMapper.class})
public interface QuestionMapper {

    @Mapping(target = "questionValue", source = "questionText")
    @Mapping(target = "topicId", source = "topic.id")
    QuestionDto toQuestionDto(Question question);

    @Mapping(source = "questionValue", target = "questionText")
    @Mapping(source = "topicId", target = "topic.id")
    Question toQuestionEntity(QuestionDto questionDto);

}
