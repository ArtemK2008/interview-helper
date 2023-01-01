package com.project.green.dto;

import com.project.green.entities.Question;

public class AnswerDto {

    private int id;
    private int voiceCount;
    private String answerText;
    private boolean isDefault;
    private Question question;

    public AnswerDto() {
    }

    public AnswerDto(int id, int voiceCount, String answerText, boolean isDefault, Question question) {
        this.id = id;
        this.voiceCount = voiceCount;
        this.answerText = answerText;
        this.isDefault = isDefault;
        this.question = question;
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
