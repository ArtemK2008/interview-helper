package com.project.green.dto;

import com.project.green.entities.Role;
import com.project.green.entities.Statistics;
import com.project.green.entities.Topic;

import java.util.Set;

public class PersonDto {

    private int id;
    private String fullName;
    private String email;
    private String password;
    private int statisticsId;

    private Set<TopicDto> topics;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getStatisticsId() {
        return statisticsId;
    }

    public void setStatisticsId(int statisticsId) {
        this.statisticsId = statisticsId;
    }

    public Set<TopicDto> getTopics() {
        return topics;
    }

    public void setTopics(Set<TopicDto> topics) {
        this.topics = topics;
    }
}
