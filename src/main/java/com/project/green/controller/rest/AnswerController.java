package com.project.green.controller.rest;

import com.project.green.dto.AnswerDto;
import com.project.green.entities.Answer;
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

    @PostMapping("/create")
    public void addNewAnswer(@RequestBody AnswerDto answerDto) {
        answerService.saveAnswer(answerDto);
    }

    @GetMapping("/all")
    public List<AnswerDto> getAllAnswers(){
        return answerService.getAllAnswersToQuestion();
    }

    @GetMapping("/{id}")
    public AnswerDto getAnswer(@PathVariable int id){
        return answerService.getById(id);
    }

    @PutMapping
    public AnswerDto updateAnswer(@RequestBody AnswerDto answerDto) {
        return answerService.updateAnswer(answerDto);
    }

    @DeleteMapping("/{id}")
    public void deleteAnswer(@PathVariable int id) {
        answerService.deleteAnswer(id);
    }

}
