package com.project.green.dto;

import com.project.green.entities.Person;
import com.project.green.entities.Question;

import java.util.Objects;
import java.util.Set;

public class StatisticsDto {
    private int personId;

    private int countOfCorrectAnswers;

    private int countOfIncorrectAnswers;

    private Set<QuestionDto> questionsAnsweredWrong;


    public StatisticsDto() {
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public int getCountOfCorrectAnswers() {
        return countOfCorrectAnswers;
    }

    public void setCountOfCorrectAnswers(int countOfCorrectAnswers) {
        this.countOfCorrectAnswers = countOfCorrectAnswers;
    }

    public int getCountOfIncorrectAnswers() {
        return countOfIncorrectAnswers;
    }

    public void setCountOfIncorrectAnswers(int countOfIncorrectAnswers) {
        this.countOfIncorrectAnswers = countOfIncorrectAnswers;
    }

    public Set<QuestionDto> getQuestionsAnsweredWrong() {
        return questionsAnsweredWrong;
    }

    public void setQuestionsAnsweredWrong(Set<QuestionDto> questionsAnsweredWrong) {
        this.questionsAnsweredWrong = questionsAnsweredWrong;
    }

    @Override
    public String toString() {
        return "StatisticsDto{" +
                "personId=" + personId +
                ", countOfCorrectAnswers=" + countOfCorrectAnswers +
                ", countOfIncorrectAnswers=" + countOfIncorrectAnswers +
                ", questionsAnsweredWrong=" + questionsAnsweredWrong +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StatisticsDto that = (StatisticsDto) o;
        return personId == that.personId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(personId);
    }
}
