package com.project.green.dto;

import com.project.green.entities.Question;

import java.util.Objects;

public class AnswerDto {

    private int id;
    private String answerText;
    private boolean isDefault;
    private Question question;

    public AnswerDto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean aDefault) {
        isDefault = aDefault;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    @Override
    public String toString() {
        return "AnswerDto{" +
                "id=" + id +
                ", answerText='" + answerText + '\'' +
                ", isDefault=" + isDefault +
                ", question=" + question +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnswerDto answerDto = (AnswerDto) o;
        return id == answerDto.id && isDefault == answerDto.isDefault && Objects.equals(answerText, answerDto.answerText) && Objects.equals(question, answerDto.question);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, answerText, isDefault, question);
    }

}
