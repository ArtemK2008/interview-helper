package com.project.green.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedSubgraph;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import java.util.Objects;
import java.util.Set;

@Entity
@NamedEntityGraph(name = "person-entity-graph",
attributeNodes = {
        @NamedAttributeNode("roles") ,
        @NamedAttributeNode(value = "statistics", subgraph = "statistics-subgraph"),
        @NamedAttributeNode(value = "savedQuestions", subgraph = "savedQuestions-subgraph"),
        @NamedAttributeNode(value = "topics", subgraph = "topics-subgraph")
},
        subgraphs =
                {@NamedSubgraph(
                        name = "statistics-subgraph",
                        attributeNodes =  {
                                @NamedAttributeNode("questionsAnsweredWrong"),
                        }
                ),
                        @NamedSubgraph(
                                name ="savedQuestions-subgraph",
                                attributeNodes = {
                                        @NamedAttributeNode("topic"),
                                        @NamedAttributeNode("answers"),
                                        @NamedAttributeNode("personsWhoSavedThis")
                                }),
                        @NamedSubgraph(
                                name ="topics-subgraph",
                                attributeNodes = {
                                        @NamedAttributeNode("questions"),
                                        @NamedAttributeNode("children"),
                                        @NamedAttributeNode("childTopic")
                                })
                }
)
public class Person {

  @Id
  @Column(name = "id")
  @SequenceGenerator(name = "person_seq", sequenceName = "person_id_seq", initialValue = 1, allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "person_seq")
  private int id;

  @Column(name = "name")
  private String fullName;

  @Column(name = "email")
  private String email;

  @Column(name = "password")
  private String password;

  @OneToOne(mappedBy="person")
  private Statistics statistics;

  @ManyToMany(mappedBy = "personsWhoSavedThis")
  private Set<Question> savedQuestions;

  @ManyToMany(mappedBy = "people", fetch = FetchType.LAZY)
  private Set<Role> roles;

  @ManyToMany
  @JoinTable(name = "Person_to_topic",
          joinColumns = @JoinColumn(name = "person_id", referencedColumnName = "id"),
          inverseJoinColumns = @JoinColumn(name = "topic_id", referencedColumnName = "id")
  )
  private Set<Topic> topics;

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

  public Set<Role> getRoles() {
    return roles;
  }

  public void setRoles(Set<Role> roles) {
    this.roles = roles;
  }

  public Set<Topic> getTopics() {
    return topics;
  }

  public void setTopics(Set<Topic> topics) {
    this.topics = topics;
  }

  @Override
  public String toString() {
    return "Person{" +
            "id=" + id +
            ", fullName='" + fullName + '\'' +
            ", email='" + email + '\'' +
            ", password='" + password + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Person person = (Person) o;
    return id == person.id && Objects.equals(fullName, person.fullName) && Objects.equals(email, person.email) && Objects.equals(password, person.password);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, fullName, email, password);
  }
}
