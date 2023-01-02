package com.project.green.entities;

import javax.persistence.*;
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

    @ManyToOne
    @JoinColumn(name = "child_topic_id")
    private Topic childTopic;

    @OneToMany(mappedBy = "childTopic")
    private Set<Topic> children;

    @OneToMany(mappedBy = "topic")
    private Set<Question> questions;

    @ManyToMany(mappedBy = "topics")
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
        return id == topic.id && Objects.equals(title, topic.title) && Objects.equals(children, topic.children);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, children);
    }
}
