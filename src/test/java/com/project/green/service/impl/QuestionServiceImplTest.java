package com.project.green.service.impl;

import com.project.green.dao.QuestionDao;
import com.project.green.dto.QuestionDto;
import com.project.green.entities.Question;
import com.project.green.entities.Topic;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@RunWith(SpringRunner.class)
class QuestionServiceImplTest {

    @Autowired
    QuestionServiceImpl questionService;

    @MockBean
    QuestionDao questionDao;

    private Question question;
    private QuestionDto questionDto;

    @BeforeEach
    void init(){
        question = createQuestionForTesting(1, "value", 1);
        questionDto = createQuestionDtoForTesting(1, "value", 1);
    }

    @Test
    void save_shouldSaveNewQuestion() {
        questionService.save(questionDto);
        Mockito.verify(questionDao, Mockito.times(1)).save(question);
    }

    @Test
    void save_shouldBeFailTest_questionIsPresent() {
        Mockito.doReturn(Optional.ofNullable(question))
                        .when(questionDao)
                                .getByValue("value");

        assertThatThrownBy(() -> questionService.save(questionDto))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(String.format("Question usually is present"));

        Mockito.verify(questionDao, Mockito.times(0)).save(question);
    }

    @Test
    void update() {
    }

    @Test
    void getAll() {
    }

    @Test
    void getById_whenGetExistingQuestion() throws Exception {

    }

    @Test
    void deleteById() {
    }

    @Test
    void findAllByTopicId() {
    }

    @Test
    void countQuestionsByTopic() {
    }

    private QuestionDto createQuestionDtoForTesting(int id, String value, int topicId) {
        QuestionDto questionDto = new QuestionDto();
        questionDto.setId(id);
        questionDto.setQuestionValue(value);
        questionDto.setTopicId(topicId);
        return questionDto;
    }

    private Question createQuestionForTesting(int id, String value, int topicId){
        Question question = new Question();
        question.setId(id);
        question.setQuestionText(value);
        Topic topic = new Topic();
        topic.setId(topicId);
        question.setTopic(topic);
        return question;
    }
}