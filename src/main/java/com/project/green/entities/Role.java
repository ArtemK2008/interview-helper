package com.project.green.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Role {

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "role_seq", sequenceName = "role_sequence", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_seq")
    private int id;

    @Column(name = "position")
    private String position;

    @ManyToMany
    @JoinTable(name = "Person_to_roles",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "person_id")
    )
    Set<Person> people;

    public Role() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Set<Person> getPeople() {
        return people;
    }

    public void setPeople(Set<Person> people) {
        this.people = people;
    }
}
