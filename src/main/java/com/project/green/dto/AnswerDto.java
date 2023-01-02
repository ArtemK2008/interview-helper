package com.project.green.dto;

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
}
