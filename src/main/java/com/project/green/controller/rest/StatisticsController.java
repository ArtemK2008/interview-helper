package com.project.green.controller.rest;

import com.project.green.dto.QuestionDto;
import com.project.green.service.StatisticsService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/statistics")
@Tag(name = "Statistics Controller", description = "controller for operation with person statistics")
public class StatisticsController {

    @Autowired
    private StatisticsService statisticsService;

    @GetMapping("/unanswered-questions/{id}")
    public List<QuestionDto> getUnansweredQuestionsById(@PathVariable("id") int id) {
        return statisticsService.getUnansweredQuestionsById(id);
    }

    @GetMapping("/correct-count/{id}")
    public int getCorrectCount(@PathVariable("id") int id) {
        return statisticsService.getCorrectCount(id);
    }

    @GetMapping("/incorrect-count/{id}")
    public int getIncorrectCount(@PathVariable("id") int id) {
        return statisticsService.getIncorrectCount(id);
    }

    @PostMapping("/add-question-to-statistics/{id}")
    public  void addQuestionToStatistics(@PathVariable("id") int id, @RequestBody @Valid QuestionDto questionDto) {
        statisticsService.addQuestionToStatistics(id, questionDto);
    }

    @DeleteMapping("/remove-question-to-statistics/{id}")
    public void  removeQuestionFromStatistics(@PathVariable("id") int id, @RequestBody @Valid QuestionDto questionDto) {
        statisticsService.removeQuestionFromStatistics(id, questionDto);
    }
}
