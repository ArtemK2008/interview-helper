package com.project.green.dto;

import java.util.Objects;

public class AnswerDto {

    private int id;
    private int voiceCount;
    private String answerText;
    private boolean isDefault;
    private int questionId;

    public AnswerDto() {
    }

    public AnswerDto(int id, int voiceCount, String answerText, boolean isDefault, int questionId) {
        this.id = id;
        this.voiceCount = voiceCount;
        this.answerText = answerText;
        this.isDefault = isDefault;
        this.questionId = questionId;
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

    public int getVoiceCount() {
        return voiceCount;
    }

    public void setVoiceCount(int voiceCount) {
        this.voiceCount = voiceCount;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    @Override
    public String toString() {
        return "AnswerDto{" +
                "id=" + id +
                ", voiceCount=" + voiceCount +
                ", answerText='" + answerText + '\'' +
                ", isDefault=" + isDefault +
                ", questionId=" + questionId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnswerDto answerDto = (AnswerDto) o;
        return id == answerDto.id && voiceCount == answerDto.voiceCount && isDefault == answerDto.isDefault && questionId == answerDto.questionId && Objects.equals(answerText, answerDto.answerText);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, voiceCount, answerText, isDefault, questionId);
    }
}
