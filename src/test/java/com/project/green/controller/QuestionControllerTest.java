package com.project.green.controller;

import com.project.green.controller.rest.QuestionController;
import com.project.green.dto.QuestionDto;
import com.project.green.exception.EntityNotFoundException;
import com.project.green.service.QuestionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(QuestionController.class)
@RunWith(SpringRunner.class)
class QuestionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private QuestionService questionService;

    private QuestionDto questionDto;

    @BeforeEach
    void init(){
        questionDto = createQuestionDtoForTesting(1,"value", 1);
    }

    @Test
    void findAll() {
    }

    @Test
    void getQuestionById() throws Exception{
        doReturn(questionDto).when(questionService).getById(anyInt());

        mockMvc.perform(get("/question/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpectAll(
                        status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON))
                                .andExpect(jsonPath("$.id").value(questionDto.getId()))
                                .andExpect(jsonPath("$.questionText").value(questionDto.getQuestionValue()));
    }

    @Test
    void getQuestionById_whenGetNotExisting_thenStatus404anExceptionThrown() throws Exception {
        mockMvc.perform(
                        get("/question/1"))
                .andExpect(status().isNotFound())
                .andExpect(mvcResult -> mvcResult.getResolvedException().getClass().equals(EntityNotFoundException.class));
    }

    @Test
    void getQuestionByTopicId() throws Exception {
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
}