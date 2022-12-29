package com.project.green.mapper;

import com.project.green.dto.QuestionDto;
import com.project.green.entities.Question;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface QuestionMapper {

    @Mapping(target = "questionValue", source = "questionText")
    QuestionDto toQuestionDto(Question question);

    @Mapping(source = "questionValue", target = "questionText")
    Question toQuestionEntity(QuestionDto questionDto);

}
