package com.project.green.controller.rest;

import com.project.green.dto.AnswerDto;
import com.project.green.dto.PersonDto;
import com.project.green.dto.QuestionDto;
import com.project.green.dto.TopicDto;
import com.project.green.service.AnswerService;
import com.project.green.service.PersonService;
import com.project.green.service.QuestionService;
import com.project.green.service.TopicService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/admin")
@Tag(name = "Admin Controller", description = "controller for admin operations")
public class AdminController {

    @Autowired
    private PersonService personService;

    @Autowired
    private AnswerService answerService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private TopicService topicService;

    @PostMapping("/person/create")
    public void create(@RequestBody @Valid PersonDto personDto) {
        personService.save(personDto);
    }

    @GetMapping("/person/all")
    public List<PersonDto> findAll() {
        return personService.getAll();
    }

    @DeleteMapping("/person/delete")
    public void delete(@RequestBody @Valid PersonDto personDto) {
        personService.deleteById(personDto.getId());
    }

    @DeleteMapping("/answer/delete")
    public void deleteAnswer(@RequestBody @Valid AnswerDto answerDto) {
        answerService.deleteAnswer(answerDto.getId());
    }

    @PutMapping("/answer/update")
    public AnswerDto updateAnswer(@RequestBody @Valid AnswerDto answerDto) {
        return answerService.updateAnswer(answerDto);
    }

    @GetMapping("/answer/all")
    public List<AnswerDto> getAllAnswers() {
        return answerService.getAllExistingAnswers();
    }

    @PostMapping("/answer/create")
    public void addNewAnswer(@RequestBody @Valid AnswerDto answerDto) {
        answerService.saveAnswer(answerDto);
    }

    @PostMapping("/question/create")
    public void create(@RequestBody @Valid QuestionDto questionDto) {
        questionService.save(questionDto);
    }

    @PutMapping("/question/update")
    public QuestionDto updateQuestion(@RequestBody @Valid QuestionDto questionDto) {
        return questionService.update(questionDto);
    }

    @DeleteMapping("/question/delete")
    public void delete(@RequestBody @Valid QuestionDto questionDto) {
        questionService.deleteById(questionDto.getId());
    }

    @PostMapping("/topic/create")
    public void create(@RequestBody @Valid TopicDto topicDto) {
        topicService.save(topicDto);
    }

    @PutMapping("/topic/update")
    public TopicDto update(@RequestBody @Valid TopicDto topicDto) {
        return topicService.update(topicDto);
    }

    @DeleteMapping("/topic/delete")
    public void delete(@RequestBody @Valid TopicDto topicDto) {
        topicService.deleteById(topicDto.getId());
    }
}
