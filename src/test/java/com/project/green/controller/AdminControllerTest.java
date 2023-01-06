package com.project.green.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.green.dto.AnswerDto;
import com.project.green.entities.Question;
import com.project.green.service.AnswerService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AdminControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AnswerService answerService;

    @Test
    void deleteAnswer() throws Exception {
        AnswerDto answerDto = new AnswerDto(1, 3, "Answer Text Example", true, 1);
        answerService.deleteAnswer(answerDto.getId());
        mockMvc.perform(delete("/api/answer/1"))
                .andExpect(status().isOk());
    }

    @Test
    void updateAnswer() throws Exception {
        AnswerDto answerDto = new AnswerDto(1, 3, "Answer Text Example", true, 1);
        Mockito.when(answerService.updateAnswer(Mockito.any())).thenReturn(answerDto);
        mockMvc.perform(put("/api/answer")
                        .content(objectMapper.writeValueAsString(answerDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(answerDto)));
    }

    @Test
    void getAllAnswers() throws Exception {
        AnswerDto answerDto1 = new AnswerDto(1, 3, "Answer1 Text Example", true, 1);
        AnswerDto answerDto2 = new AnswerDto(2, 2, "Answer2 Text Example", false, 1);
        Mockito.when(answerService.getAllAnswers()).thenReturn(Arrays.asList(answerDto1, answerDto2));
        mockMvc.perform(get("/api/answer/all"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(Arrays.asList(answerDto1, answerDto2))));
    }

    @Test
    void addNewAnswer() throws Exception {
        AnswerDto answerDto = new AnswerDto(1, 3, "Answer Text Example", true, 1);
        Mockito.when(answerService.saveAnswer(Mockito.any())).thenReturn(answerDto);
        mockMvc.perform(post("/api/answer/create")
                        .content(objectMapper.writeValueAsString(answerDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}