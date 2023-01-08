package com.project.green.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.green.controller.rest.AnswerController;
import com.project.green.dto.AnswerDto;
import com.project.green.service.AnswerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(AnswerController.class)
@RunWith(SpringRunner.class)
public class AnswerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AnswerService answerService;

    @Test
    public void getAnswer() throws Exception {
        AnswerDto answerDto = new AnswerDto(1, 3, "Answer Text Example", true, 1);
        Mockito.when(answerService.getById(1)).thenReturn(answerDto);
        mockMvc.perform(get("/api/answer/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(answerDto)));
    }
}
