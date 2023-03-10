package com.project.green.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedSubgraph;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@NamedEntityGraph(name = "question-entity-graph",
        attributeNodes = {
                @NamedAttributeNode(value = "answers", subgraph = "answers-subgraph"),
                @NamedAttributeNode(value = "personsWhoSavedThis", subgraph = "personsWhoSavedThis-subgraph"),
                @NamedAttributeNode(value = "topic", subgraph = "topic-subgraph")
        },
        subgraphs =
                {@NamedSubgraph(
                        name = "answers-subgraph",
                        attributeNodes =  {
                                @NamedAttributeNode("question"),
                        }
                ),
                        @NamedSubgraph(
                                name ="personsWhoSavedThis-subgraph",
                                attributeNodes = {
                                        @NamedAttributeNode("statistics"),
                                        @NamedAttributeNode("savedQuestions"),
                                        @NamedAttributeNode("roles"),
                                        @NamedAttributeNode("topics")
                                }),
                        @NamedSubgraph(
                                name ="topic-subgraph",
                                attributeNodes = {
                                        @NamedAttributeNode("questions"),
                                        @NamedAttributeNode("children"),
                                        @NamedAttributeNode("childTopic")
                                })
                }
)
public class Question {

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "question_seq", sequenceName = "question_id_seq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "question_seq")
    private int id;

    @Column(name = "question")
    private String questionText;

    @ManyToOne
    @JoinColumn(name = "topic_id")
    private Topic topic;

    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "question")
    private Set<Answer> answers;

    @ManyToMany
    @JoinTable(name = "Person_to_question",
            joinColumns = @JoinColumn(name = "question_id"),
            inverseJoinColumns = @JoinColumn(name = "person_id")
    )
    private List<Person> personsWhoSavedThis;

    public Question() {
    }

    public Question(int id, String questionText, Topic topic, Set<Answer> answers, List<Person> personsWhoSavedThis) {
        this.id = id;
        this.questionText = questionText;
        this.topic = topic;
        this.answers = answers;
        this.personsWhoSavedThis = personsWhoSavedThis;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public Set<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(Set<Answer> answers) {
        this.answers = answers;
    }

    public List<Person> getPersonsWhoSavedThis() {
        return personsWhoSavedThis;
    }

    public void setPersonsWhoSavedThis(List<Person> personsWhoSavedThis) {
        this.personsWhoSavedThis = personsWhoSavedThis;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return id == question.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", questionText='" + questionText + '\'' +
                ", topic=" + topic +
                ", answers=" + answers +
                ", personsWhoSavedThis=" + personsWhoSavedThis +
                '}';
    }
}
