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

@Entity
public class Answer {

  @Id
  @Column(name = "answer_id")
  @SequenceGenerator(name = "answer_seq", sequenceName = "answer_sequence", initialValue = 1, allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "answer_seq")
  private int id;
  @Column(name = "voice_count")
  private int voiceCount;
  @Column(name = "answer")
  private String answerText;
  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "question_id")
  private Question question;

  public Answer() {
    super();
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

}