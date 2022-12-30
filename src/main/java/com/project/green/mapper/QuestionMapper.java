package com.project.green.mapper;

import com.project.green.dto.QuestionDto;
import com.project.green.entities.Question;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = {TopicMapper.class})
public interface QuestionMapper {

    @Mappings({
            @Mapping(target = "questionValue", source = "questionText")
//            @Mapping(target = "topicId", source = "question.topic.id")
    })
    QuestionDto toQuestionDto(Question question);

    @Mappings({
    @Mapping(source = "questionValue", target = "questionText")
//    @Mapping(source = "topicId", target = "question.topic.id")
    })
    Question toQuestionEntity(QuestionDto questionDto);

}
