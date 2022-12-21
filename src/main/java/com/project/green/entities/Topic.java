package com.project.green.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity
public class Topic {

  @Id
  @Column(name = "topic_id")
  @SequenceGenerator(name = "topic_seq", sequenceName = "topic_sequence", initialValue = 1, allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "topic_seq")
  private int id;
  @Column(name = "name")
  private String title;
  @OneToMany(mappedBy = "topic", cascade = CascadeType.ALL)
  private Set<Question> questions;

  public Topic() {
    super();
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Set<Question> getQuestions() {
    return questions;
  }

  public void setQuestions(Set<Question> questions) {
    this.questions = questions;
  }

}
