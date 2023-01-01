package com.project.green.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import java.util.Objects;

@Entity
public class Answer {

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "answer_seq", sequenceName = "answer_id_seq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "answer_seq")
    private int id;

    @Column(name = "voice_count")
    private int voiceCount;

    @Column(name = "answer")
    private String answerText;

    @Column(name = "isdefault")
    private boolean isDefault;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "question_id")
    private Question question;

    public Answer() {
    }

    public Answer(int id, int voiceCount, String answerText, boolean isDefault, Question question) {
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

    public int getVoiceCount() {
        return voiceCount;
    }

    public void setVoiceCount(int voiceCount) {
        this.voiceCount = voiceCount;
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean aDefault) {
        isDefault = aDefault;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "id=" + id +
                ", voiceCount=" + voiceCount +
                ", answerText='" + answerText + '\'' +
                ", isDefault=" + isDefault +
                ", question=" + question +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Answer answer = (Answer) o;
        return id == answer.id && voiceCount == answer.voiceCount && isDefault == answer.isDefault && answerText.equals(answer.answerText) && question.equals(answer.question);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, voiceCount, answerText, isDefault, question);
    }
}
