package com.project.green.controller;

import com.project.green.entities.Question;
import com.project.green.service.TransactionalQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class QuestionController {

    @Autowired
    private TransactionalQuestionService questionService;

    @GetMapping("/questions")
    public Collection<Question> findAll (Model model){
        Collection<Question> allQuestions =questionService.findAll();
        model.addAllAttributes(allQuestions);
        return allQuestions;
    }


}
