package com.project.green.dto;

import java.util.Objects;

public class QuestionDto {

    private int id;
    private String questionValue;

    private int topicId;

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

    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    @Override
    public String toString() {
        return "QuestionDto{" +
                "id=" + id +
                ", questionValue='" + questionValue + '\'' +
                ", topic_id=" + topicId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuestionDto that = (QuestionDto) o;
        return id == that.id && topicId == that.topicId && Objects.equals(questionValue, that.questionValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, questionValue, topicId);
    }

}
