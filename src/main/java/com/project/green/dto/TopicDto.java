package com.project.green.dto;

import java.util.Objects;
import java.util.Set;

public class TopicDto {

    private int id;
    private String title;
    private Set<TopicDto> children;

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


    public Set<TopicDto> getChildren() {
        return children;
    }

    public void setChildren(Set<TopicDto> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "TopicDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", children=" + children +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TopicDto topicDto = (TopicDto) o;
        return id == topicDto.id && Objects.equals(title, topicDto.title) && Objects.equals(children, topicDto.children);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, children);
    }

}
