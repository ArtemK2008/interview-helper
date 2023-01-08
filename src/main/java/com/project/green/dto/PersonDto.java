package com.project.green.dto;

import com.project.green.entities.Question;

import java.util.Objects;
import java.util.Set;

public class PersonDto {

    private int id;
    private String fullName;
    private String email;
    private String password;
    private int statisticsId;
    private Set<Question> savedQuestions;
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

    public Set<Question> getSavedQuestions() {
        return savedQuestions;
    }

    public void setSavedQuestions(Set<Question> savedQuestions) {
        this.savedQuestions = savedQuestions;
    }

    public Set<TopicDto> getTopics() {
        return topics;
    }

    public void setTopics(Set<TopicDto> topics) {
        this.topics = topics;
    }

    @Override
    public String toString() {
        return "PersonDto{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", statisticsId=" + statisticsId +
                ", topics=" + topics +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonDto personDto = (PersonDto) o;
        return id == personDto.id && statisticsId == personDto.statisticsId && Objects.equals(fullName, personDto.fullName) && Objects.equals(email, personDto.email) && Objects.equals(password, personDto.password) && Objects.equals(topics, personDto.topics);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fullName, email, password, statisticsId, topics);
    }
}
