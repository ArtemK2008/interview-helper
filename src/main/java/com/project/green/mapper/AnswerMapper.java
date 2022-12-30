package com.project.green.mapper;

import com.project.green.dto.AnswerDto;
import com.project.green.entities.Answer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AnswerMapper {

    AnswerDto toAnswerDto(Answer question);

    Answer toAnswer(AnswerDto answerDto);
}
