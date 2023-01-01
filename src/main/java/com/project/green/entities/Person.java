package com.project.green.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import java.util.Objects;
import java.util.Set;

@Entity
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

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "person")
    private Statistics statistics;

    @ManyToMany(cascade = CascadeType.MERGE, mappedBy = "personsWhoSavedThis")
    private Set<Question> savedQuestions;

    @ManyToMany(cascade = CascadeType.MERGE, mappedBy = "people")
    private Set<Role> roles;

    @ManyToMany(cascade = CascadeType.MERGE, mappedBy = "people")
    private Set<Topic> topics;

    public Person() {
    }

    public Person(int id, String fullName, String email, String password, Statistics statistics,
                  Set<Question> savedQuestions, Set<Role> roles, Set<Topic> topics) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.statistics = statistics;
        this.savedQuestions = savedQuestions;
        this.roles = roles;
        this.topics = topics;
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
                ", statistics=" + statistics +
                ", savedQuestions=" + savedQuestions +
                ", roles=" + roles +
                ", topics=" + topics +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id == person.id && Objects.equals(fullName, person.fullName) && Objects.equals(email, person.email) && Objects.equals(password, person.password) && Objects.equals(statistics, person.statistics) && Objects.equals(savedQuestions, person.savedQuestions) && Objects.equals(roles, person.roles) && Objects.equals(topics, person.topics);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fullName, email, password, statistics, savedQuestions, roles, topics);
    }
}
