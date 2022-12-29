package com.project.green.controller;

import com.project.green.dto.QuestionDto;
import com.project.green.entities.Question;
import com.project.green.mapper.QuestionMapper;
import com.project.green.service.TransactionalQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
public class QuestionController {

    @Autowired
    private TransactionalQuestionService questionService;
    @Autowired
    private QuestionMapper questionMapper;


    @GetMapping("/questions")
    public Collection<QuestionDto> findAll (){
        Collection<Question> allQuestions =questionService.findAll();
        return  allQuestions.stream()
                .map(item->questionMapper.toQuestionDto(item))
                .collect(Collectors.toList());
    }

    @PostMapping("/question")
    public String save (QuestionDto questionDto){
        questionService.createQuestion(questionMapper.toQuestionEntity(questionDto));
        return "/question";
    }


}
