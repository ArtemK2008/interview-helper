package com.project.green.controller.mvc;

import com.project.green.dto.AnswerDto;
import com.project.green.dto.QuestionDto;
import com.project.green.service.AnswerService;
import com.project.green.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@Scope("session")
public class AnswerControllerMvc {

    @Autowired
    QuestionService questionService;

    @Autowired
    AnswerService answerService;

    @PostMapping("/display-answer-card")
    public RedirectView displayAnswerPage(@RequestParam("questionValue") String question,
                                          HttpServletRequest request, RedirectAttributes redirectAttributes) {
        RedirectView redirectView = new RedirectView();
        redirectView.setContextRelative(true);

        QuestionDto currQuestion = questionService.getByValue(question);
        int id = currQuestion.getId();
        AnswerDto bestAnswer = answerService.getBestByQuestionId(id);
        String answerText = bestAnswer.getAnswerText();
        List<AnswerDto> allAnswers = answerService.getAllAnswersToQuestionInOrderByVoice(id);
        if (allAnswers != null || bestAnswer != null) {
            allAnswers.remove(bestAnswer);
        }
        request.getSession().setAttribute("answers", allAnswers);
        redirectAttributes.addFlashAttribute("question", question);
        redirectAttributes.addFlashAttribute("answer", answerText);

        redirectView.setUrl("/questions/display-answer/" + id);
        return redirectView;
    }

    @GetMapping("questions/display-answer/{questionId}")
    public String displayAnswer(@PathVariable("questionId") String questionId) {
        return "answer/answer-page-card";
    }

    @GetMapping("/Display-other-card-answers")
    public String displayOtherAnswwers(HttpServletRequest request, Model model) {
        List<AnswerDto> answers = (List<AnswerDto>) request.getSession().getAttribute("answers");
        request.getSession().removeAttribute("answers");
        model.addAttribute("answers", answers);
        return "/answer/show-other-possible-card-answers";
    }
}
