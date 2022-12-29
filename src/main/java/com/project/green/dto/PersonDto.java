package com.project.green.dto;

import com.project.green.entities.Role;
import com.project.green.entities.Statistics;
import com.project.green.entities.Topic;

import java.util.Set;

public class PersonDto {

    private int id;
    private String fullName;
    private String email;
    private Statistics statistics;
    private Set<Role> roles;
    private Set<Topic> topics;

    public PersonDto() {
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

    public Statistics getStatistics() {
        return statistics;
    }

    public void setStatistics(Statistics statistics) {
        this.statistics = statistics;
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
}
