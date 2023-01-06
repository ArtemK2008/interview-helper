package com.project.green.controller;

import com.project.green.dto.QuestionDto;
import com.project.green.dto.TopicDto;
import com.project.green.service.QuestionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/question")
@Tag(name = "Question Controller", description = "controller for operation by question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/all")
    public List<QuestionDto> findAll() {
        return questionService.getAll();
    }

    @GetMapping("/{id}")
    public QuestionDto getQuestionById(@PathVariable("id") int id) {
        return questionService.getById(id);
    }

    @GetMapping("/all-by-topic/{id}")
    public List<QuestionDto> getQuestionByTopicId(@PathVariable("id") int id) {
        return questionService.findAllByTopicId(id);
    }

    @GetMapping("/count-by-topic")
    public List<TopicDto> countQuestionsByTopic() {
        return questionService.countQuestionsByTopic();
    }
}
