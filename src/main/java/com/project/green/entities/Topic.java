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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import java.util.Objects;
import java.util.Set;

@Entity
public class Topic {

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "topic_seq", sequenceName = "topic_id_seq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "topic_seq")
    private int id;

    @Column(name = "name")
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "child_topic_id")
    private Topic childTopic;


    @OneToMany(mappedBy = "childTopic")
    private Set<Topic> children;

    @OneToMany(mappedBy = "topic")
    private Set<Question> questions;

    @ManyToMany
    @JoinTable(name = "Person_to_topic",
            joinColumns = @JoinColumn(name = "topic_id"),
            inverseJoinColumns = @JoinColumn(name = "person_id")
    )
    private Set<Person> people;

    public Topic() {
    }

    public Topic(int id, String title, Topic childTopic, Set<Topic> children, Set<Question> questions,
                 Set<Person> people) {
        this.id = id;
        this.title = title;
        this.childTopic = childTopic;
        this.children = children;
        this.questions = questions;
        this.people = people;
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

    @Override
    public String toString() {
        return "Topic{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", children=" + children +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Topic topic = (Topic) o;
        return id == topic.id && Objects.equals(title, topic.title) && Objects.equals(childTopic, topic.childTopic) && Objects.equals(children, topic.children) && Objects.equals(questions, topic.questions) && Objects.equals(people, topic.people);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, childTopic, children, questions, people);
    }

}
