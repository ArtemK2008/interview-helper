package com.project.green.controller;

import com.project.green.dto.AnswerDto;
import com.project.green.service.AnswerService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/answer")
@Tag(name = "Answer Controller", description = "controller for operation by answer")
public class AnswerController {

    @Autowired
    private AnswerService answerService;

    @GetMapping("/{id}")
    public AnswerDto getAnswer(@PathVariable int id) {
        return answerService.getById(id);
    }

    @GetMapping("/all-in-order/{id}")
    public List<AnswerDto> getAllAnswersToQuestionInOrderByVoice(@PathVariable int id) {
        return answerService.getAllAnswersToQuestionInOrderByVoice(id);
    }
}
