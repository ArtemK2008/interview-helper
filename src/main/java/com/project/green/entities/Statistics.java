package com.project.green.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class Statistics {

  @Id
  @Column(name = "statistics_id")
  @SequenceGenerator(name = "statistics_seq", sequenceName = "statistics_sequence", initialValue = 1, allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "statistics_seq")
  private int id;
  @Column(name = "count_of_correct_answers")
  private int countOfCorrectAnswers;
  @Column(name = "count_of_wrong_answers")
  private int countOfIncorrectAnswers;
  @OneToOne(mappedBy = "statistics", cascade = CascadeType.ALL)
  @JoinColumn(name = "person_id")
  private Person person;
  @ManyToMany
  @JoinTable(name = "Unanswered_question_to_statistics", joinColumns = {
      @JoinColumn(name = "question_id") }, inverseJoinColumns = {
          @JoinColumn(name = "statistics_id") })
  private Set<Question> questionsAnsweredWrong;

  public Statistics() {
    super();
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getCountOfCorrectAnswers() {
    return countOfCorrectAnswers;
  }

  public void setCountOfCorrectAnswers(int countOfCorrectAnswers) {
    this.countOfCorrectAnswers = countOfCorrectAnswers;
  }

  public int getCountOfIncorrectAnswers() {
    return countOfIncorrectAnswers;
  }

  public void setCountOfIncorrectAnswers(int countOfIncorrectAnswers) {
    this.countOfIncorrectAnswers = countOfIncorrectAnswers;
  }

  public Person getPerson() {
    return person;
  }

  public void setPerson(Person person) {
    this.person = person;
  }

  public Set<Question> getQuestionsAnsweredWrong() {
    return questionsAnsweredWrong;
  }

  public void setQuestionsAnsweredWrong(Set<Question> questionsAnsweredWrong) {
    this.questionsAnsweredWrong = questionsAnsweredWrong;
  }

}
