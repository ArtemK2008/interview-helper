package com.project.green.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class Person {

  @Id
  @Column(name = "person_id")
  @SequenceGenerator(name = "person_seq", sequenceName = "person_sequence", initialValue = 1, allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "person_seq")
  private int id;
  @Column(name = "name")
  private String fullName;
  @Column(name = "email")
  private String email;
  @Column(name = "password")
  private String password;
  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "statistics_id")
  private Statistics statistics;
  @ManyToMany(cascade = CascadeType.MERGE, mappedBy = "personsWhoSavedThis")
  private Set<Question> savedQuestions;

  public Person() {
    super();
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Statistics getStatistics() {
    return statistics;
  }

  public void setStatistics(Statistics statistics) {
    this.statistics = statistics;
  }

  public Set<Question> getSavedQuestions() {
    return savedQuestions;
  }

  public void setSavedQuestions(Set<Question> savedQuestions) {
    this.savedQuestions = savedQuestions;
  }

}
