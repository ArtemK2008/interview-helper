package com.project.green.dto;

import com.project.green.entities.Question;

import java.util.Objects;

public class AnswerDto {

    private int id;
    private int voiceCount;
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

    public int getVoiceCount() {
        return voiceCount;
    }

    public void setVoiceCount(int voiceCount) {
        this.voiceCount = voiceCount;
    }

}
