package com.project.green.dto;

import com.project.green.entities.Person;
import com.project.green.entities.Topic;

import java.util.Set;

public class TopicDto {

    private int id;
    private String title;
    private Topic childTopic;
    private Set<Topic> children;
    private Set<Person> people;

    public TopicDto() {
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
