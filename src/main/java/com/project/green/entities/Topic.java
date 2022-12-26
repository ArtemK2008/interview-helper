package com.project.green.entities;

import java.util.Set;

import javax.persistence.*;

@Entity
public class Topic {

  @Id
  @Column(name = "id")
  @SequenceGenerator(name = "topic_seq", sequenceName = "topic_sequence", initialValue = 1, allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "topic_seq")
  private int id;

  @Column(name = "name")
  private String title;

  @ManyToOne
  @JoinColumn(name = "child_topic_id")
  private Topic childTopic;

  @OneToMany(mappedBy = "childTopic")
  private Set<Topic> children;

  @OneToMany(mappedBy = "topic", cascade = CascadeType.ALL)
  private Set<Question> questions;

  @ManyToMany
  @JoinTable(name = "Person_to_topic",
          joinColumns = @JoinColumn(name = "topic_id"),
          inverseJoinColumns = @JoinColumn(name = "person_id")
  )
  private Set<Person> people;

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

  public Topic getChildTopic() {
    return childTopic;
  }

  public void setChildTopic(Topic childTopic) {
    this.childTopic = childTopic;
  }

  public Set<Topic> getChildren() {
    return children;
  }

  public void setChildren(Set<Topic> children) {
    this.children = children;
  }

  public Set<Person> getPeople() {
    return people;
  }

  public void setPeople(Set<Person> people) {
    this.people = people;
  }
}
