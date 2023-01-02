package com.project.green.mapper;

import com.project.green.dto.AnswerDto;
import com.project.green.entities.Answer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AnswerMapper {

    @Mapping(target = "questionId", source = "question.id")
    AnswerDto toAnswerDto(Answer question);

    @Mapping(source = "questionId", target = "question.id")
    Answer toAnswer(AnswerDto answerDto);
}
