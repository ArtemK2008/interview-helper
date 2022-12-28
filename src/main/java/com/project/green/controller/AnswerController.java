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
    public void addNewAnswer(@RequestBody Answer answer) {
        answerService.saveAnswer(answer);
    }

    @GetMapping
    public List<Answer> getAllAnswers(){
        List<Answer> answers = answerService.getAllAnswersToQuestion();
        return answers;
    }

//    @GetMapping
//    public List<Answer> getAllAnswersToQuestion(){
//        return null;
//    }

    @GetMapping("/{id}")
    public Answer getAnswer(@PathVariable int id){
        return answerService.getById(id);
    }

    @PutMapping
    public Answer updateAnswer(@RequestBody Answer answer) {
        answerService.updateAnswer(answer);
        return answer;
    }

    @DeleteMapping("/{id}")
    public void deleteAnswer(@PathVariable int id) {
        answerService.deleteAnswer(id);
    }
}
