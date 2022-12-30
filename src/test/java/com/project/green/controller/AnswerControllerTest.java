package com.project.green.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.green.entities.Answer;
import com.project.green.entities.Question;
import com.project.green.service.AnswerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;


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
    public void addNewAnswer() throws Exception {
        Answer answer = new Answer(1, 3, "Answer Text Example", true, new Question());
        Mockito.when(answerService.saveAnswer(Mockito.any())).thenReturn(answer);
        mockMvc.perform(post("/answers")
                .content(objectMapper.writeValueAsString(answer))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getAllAnswers() throws Exception {
        Answer answer1 = new Answer(1, 3, "Answer1 Text Example", true, new Question());
        Answer answer2 = new Answer(2, 2, "Answer2 Text Example", false, new Question());
        Mockito.when(answerService.getAllAnswersToQuestion()).thenReturn(Arrays.asList(answer1,answer2));
        mockMvc.perform(get("/answers"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(Arrays.asList(answer1,answer2))));
    }

    @Test
    public void getAnswer() throws Exception {
        Answer answer = new Answer(1, 3, "Answer Text Example", true, new Question());
        Mockito.when(answerService.getById(1)).thenReturn(answer);
        mockMvc.perform(get("/answers/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(answer)));
    }

    @Test
    public void updateAnswer() throws Exception {
        Answer answer = new Answer(1, 3, "Answer Text Example", true, new Question());
        Mockito.when(answerService.updateAnswer(Mockito.any())).thenReturn(answer);
        mockMvc.perform(put("/answers")
                .content(objectMapper.writeValueAsString(answer))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(answer)));
    }

    @Test
    public void deleteAnswer() throws Exception {
        Answer answer = new Answer(1, 3, "Answer Text Example", true, new Question());
        answerService.deleteAnswer(answer.getId());
        mockMvc.perform(delete("/answers/1"))
                .andExpect(status().isOk());
    }
}