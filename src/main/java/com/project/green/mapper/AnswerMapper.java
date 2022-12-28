package com.project.green.mapper;

import com.project.green.dto.AnswerDto;
import com.project.green.dto.QuestionDto;
import com.project.green.entities.Answer;
import com.project.green.entities.Question;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AnswerMapper {

    AnswerDto toAnswerDto(Answer question);

    Answer toAnswer(AnswerDto answerDto);
}
