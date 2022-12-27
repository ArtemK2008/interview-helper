package com.project.green.controller;

import com.project.green.entities.Answer;
import com.project.green.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/answers")
public class AnswerController {


    private AnswerService answerService;

    @Autowired
    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    @PostMapping
    public Answer addNewAnswer(@RequestBody Answer answer) {
        return answerService.saveAnswer(answer);
    }

    @GetMapping
    public List<Answer> getAllAnswersToQuestion(){

        List<Answer> answers = answerService.getAllAnswersToQuestion();

        return answers;
    }

    @PutMapping
    public Answer updateAnswer(@RequestBody Answer answer) {

        answerService.updateAnswer(answer);

        return answer;
    }

    @DeleteMapping("/{id}")
    public String deleteAnswer(@PathVariable int id) {

        answerService.deleteAnswer(id);

        return "Answer was deleted";
    }
}
