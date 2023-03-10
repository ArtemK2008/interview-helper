package com.project.green.entities;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedSubgraph;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import java.util.Objects;
import java.util.Set;

@Entity
@NamedEntityGraph(name = "statistics-entity-graph",
attributeNodes = {
        @NamedAttributeNode(value = "questionsAnsweredWrong", subgraph = "questionsAnsweredWrong-subgraphs"),
        @NamedAttributeNode(value = "person", subgraph = "person-subgraph")
}
       ,
        subgraphs =
                {@NamedSubgraph(
                        name = "questionsAnsweredWrong-subgraphs",
                        attributeNodes =  {
                                @NamedAttributeNode("topic"),
                                @NamedAttributeNode("answers"),
                                @NamedAttributeNode("personsWhoSavedThis")
                        }
                ),
                @NamedSubgraph(
                        name ="person-subgraph",
                        attributeNodes = {
                        @NamedAttributeNode("statistics"),
                        @NamedAttributeNode("savedQuestions"),
                        @NamedAttributeNode("roles"),
                        @NamedAttributeNode("topics")
                        })
                }
 )
public class Statistics {

    @Id
    @Column(name = "statistics_person_id")
    @GeneratedValue(generator = "gen")
    @GenericGenerator(name = "gen", strategy = "foreign",
            parameters = @Parameter(name = "property", value = "person"))
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
            joinColumns = @JoinColumn(name = "statistic_id"),
            inverseJoinColumns = @JoinColumn(name = "question_id")
    )
    private Set<Question> questionsAnsweredWrong;

    public Statistics() {
    }

    public Statistics(int personId, int countOfCorrectAnswers, int countOfIncorrectAnswers, Person person,
                      Set<Question> questionsAnsweredWrong) {
        this.personId = personId;
        this.countOfCorrectAnswers = countOfCorrectAnswers;
        this.countOfIncorrectAnswers = countOfIncorrectAnswers;
        this.person = person;
        this.questionsAnsweredWrong = questionsAnsweredWrong;
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

    @Override
    public String toString() {
        return "Statistics{" +
                "personId=" + personId +
                ", countOfCorrectAnswers=" + countOfCorrectAnswers +
                ", countOfIncorrectAnswers=" + countOfIncorrectAnswers +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Statistics that = (Statistics) o;
        return personId == that.personId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(personId);
    }
}
