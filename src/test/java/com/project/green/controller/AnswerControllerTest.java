//package com.project.green.controller;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.project.green.dto.AnswerDto;
//import com.project.green.entities.Question;
//import com.project.green.service.AnswerService;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//import java.util.Arrays;
//
//
//@WebMvcTest(AnswerController.class)
//@RunWith(SpringRunner.class)
//public class AnswerControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @MockBean
//    private AnswerService answerService;
//
//    @Test
//    public void addNewAnswer() throws Exception {
//        AnswerDto answerDto = new AnswerDto(1, 3, "Answer Text Example", true, new Question());
//        Mockito.when(answerService.saveAnswer(Mockito.any())).thenReturn(answerDto);
//        mockMvc.perform(post("/api/answer/create")
//                .content(objectMapper.writeValueAsString(answerDto))
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    public void getAllAnswers() throws Exception {
//        AnswerDto answerDto1 = new AnswerDto(1, 3, "Answer1 Text Example", true, new Question());
//        AnswerDto answerDto2 = new AnswerDto(2, 2, "Answer2 Text Example", false, new Question());
//        Mockito.when(answerService.getAllAnswersToQuestion()).thenReturn(Arrays.asList(answerDto1, answerDto2));
//        mockMvc.perform(get("/api/answer/all"))
//                .andExpect(status().isOk())
//                .andExpect(content().json(objectMapper.writeValueAsString(Arrays.asList(answerDto1, answerDto2))));
//    }
//
//    @Test
//    public void getAnswer() throws Exception {
//        AnswerDto answerDto = new AnswerDto(1, 3, "Answer Text Example", true, new Question());
//        Mockito.when(answerService.getById(1)).thenReturn(answerDto);
//        mockMvc.perform(get("/api/answer/1"))
//                .andExpect(status().isOk())
//                .andExpect(content().json(objectMapper.writeValueAsString(answerDto)));
//    }
//
//    @Test
//    public void updateAnswer() throws Exception {
//        AnswerDto answerDto = new AnswerDto(1, 3, "Answer Text Example", true, new Question());
//        Mockito.when(answerService.updateAnswer(Mockito.any())).thenReturn(answerDto);
//        mockMvc.perform(put("/api/answer")
//                .content(objectMapper.writeValueAsString(answerDto))
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(content().json(objectMapper.writeValueAsString(answerDto)));
//    }
//
//    @Test
//    public void deleteAnswer() throws Exception {
//        AnswerDto answerDto = new AnswerDto(1, 3, "Answer Text Example", true, new Question());
//        answerService.deleteAnswer(answerDto.getId());
//        mockMvc.perform(delete("/api/answer/1"))
//                .andExpect(status().isOk());
//    }
//}