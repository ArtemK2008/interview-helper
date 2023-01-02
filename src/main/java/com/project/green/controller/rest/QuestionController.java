package com.project.green.controller.rest;

import com.project.green.dto.CountQuestionDto;
import com.project.green.dto.QuestionDto;
import com.project.green.service.QuestionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/question")
@Tag(name = "Question Controller", description = "controller for operation by question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/all")
    public List<QuestionDto> findAll() {
        return questionService.findAll();
    }

    @GetMapping("/{id}")
    public QuestionDto getQuestionById(@PathVariable("id") int id) {
        return questionService.findById(id);
    }

    @PostMapping("/create")
    public void create(@RequestBody @Valid QuestionDto questionDto) {
        questionService.createQuestion(questionDto);
    }

    @PostMapping("/update")
    public QuestionDto update(@RequestBody @Valid QuestionDto questionDto) {
        return questionService.updateQuestions(questionDto);
    }

    @PostMapping("/delete")
    public void delete(@RequestBody @Valid QuestionDto questionDto) {
        questionService.deleteById(questionDto.getId());
    }

    @GetMapping("/all-by-topic")
    public List<QuestionDto> getQuestionByTopicId(int id) {
        return questionService.findAllByTopicId(id);
    }

    @GetMapping("/count-by-topic")
    public List<CountQuestionDto> countQuestionsByTopic() {
        return questionService.countQuestionsByTopic();
    }
}
