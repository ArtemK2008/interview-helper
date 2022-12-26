package com.project.green.entities;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import java.util.Set;

import javax.persistence.*;

@Entity
public class Statistics {

  @Id
  @Column(name = "person_id")
  @GeneratedValue(generator = "gen")
  @GenericGenerator(name = "gen", strategy = "foreign",
          parameters=@Parameter(name = "property", value = "person"))
  private int personId;

  @Column(name = "answer_correct_count")
  private int countOfCorrectAnswers;

  @Column(name = "answer_wrong_count")
  private int countOfIncorrectAnswers;

  @OneToOne
  @PrimaryKeyJoinColumn
  private Person person;

  @ManyToMany
  @JoinTable(name = "Statistics_to_question",
          joinColumns = @JoinColumn(name = "question_id"),
          inverseJoinColumns = @JoinColumn(name = "statistics_id")
  )
  private Set<Question> questionsAnsweredWrong;

  public Statistics() {
    super();
  }

  public int getPersonId() {
    return personId;
  }

  public void setPersonId(int personId) {
    this.personId = personId;
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
