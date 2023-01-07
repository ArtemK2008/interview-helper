package com.project.green.controller.mvc;

import com.project.green.dto.AnswerDto;
import com.project.green.dto.QuestionDto;
import com.project.green.service.AnswerService;
import com.project.green.service.QuestionService;
import com.project.green.service.StatisticsService;
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
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@Scope("session")
public class ProfileController {

    @Autowired
    StatisticsService statisticsService;

    @Autowired
    QuestionService questionService;

    @Autowired
    AnswerService answerService;

    @GetMapping("/Display-Profile")
    public String goToProfile(HttpSession session, Model model) {
        String sessionId = (String) session.getAttribute("sessionId");
        int id = Integer.valueOf(sessionId);
        int incorrectCount = statisticsService.getIncorrectCount(id);
        int correctCount = statisticsService.getCorrectCount(id);
        List<QuestionDto> unansweredQuestions = statisticsService.getUnansweredQuestionsById(id);
        List<String> questionDescribtion = unansweredQuestions.stream().map(q -> q.getQuestionValue()).collect(Collectors.toList());
        model.addAttribute("id", id);
        model.addAttribute("wrong", incorrectCount);
        model.addAttribute("correct", correctCount);
        model.addAttribute("questionList", questionDescribtion);
        return "profile/profile-page";
    }

    @PostMapping("/display-ununswered-questions")
    public RedirectView displayUnunswereedQuestion(@RequestParam("question") String question
            , RedirectAttributes redirectAttributes, HttpServletRequest request) {
        RedirectView redirectView = new RedirectView();
        redirectView.setContextRelative(true);
        QuestionDto currQuestion = questionService.getByValue(question);
        int id = currQuestion.getId();
        AnswerDto bestAnswer = answerService.getBestByQuestionId(id);
        String answerText = bestAnswer.getAnswerText();
        List<AnswerDto> allAnswers = answerService.getAllAnswersToQuestionInOrderByVoice(id);
        if(allAnswers != null || bestAnswer != null) {
            allAnswers.remove(bestAnswer);
        }
        request.getSession().setAttribute("answers" , allAnswers);
        redirectAttributes.addFlashAttribute("question", question);
        redirectAttributes.addFlashAttribute("answer", answerText);
        redirectView.setUrl("/profile/display-answer/" + id);
        return redirectView;
    }

    @GetMapping("profile/display-answer/{questionId}")
    public String displayAnswer(@PathVariable("questionId") String questionId) {
        return "profile/answer-page";
    }

    @PostMapping("/delete-question-from-statistics")
     public RedirectView deleteQuestionFromStatistics(@RequestParam("questionValue") String questionValue) {
        RedirectView redirectView = new RedirectView();
        redirectView.setContextRelative(true);
        QuestionDto currQuestion = questionService.getByValue(questionValue);
        questionService.deleteById(currQuestion.getId());
        redirectView.setUrl("/success-page");
        return  redirectView;
     }

     @GetMapping("/success-page")
     public String showSuccess() {
      return "success";
     }
    @GetMapping("/Display-other-answers")
    public String displayOtherAnswwers(HttpServletRequest request,Model model) {
        List<AnswerDto> answers =(List<AnswerDto>) request.getSession().getAttribute("answers");
        request.getSession().removeAttribute("answers");
        model.addAttribute("answers", answers);
        return "/profile/show-other-possible-answers";
    }
}
