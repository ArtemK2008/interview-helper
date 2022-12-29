package com.project.green.dto;

import com.project.green.entities.Answer;
import com.project.green.entities.Person;

import java.util.List;
import java.util.Set;

public class QuestionDto {

    private int id;
    private String questionValue;

    private TopicDto topicDto;

    private Set<AnswerDto> answersDto;

    private List<PersonDto> personsWhoSavedThis;

    public QuestionDto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestionValue() {
        return questionValue;
    }

    public void setQuestionValue(String questionValue) {
        this.questionValue = questionValue;
    }
}
