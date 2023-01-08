package com.project.green.controller.mvc;

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
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@Scope("session")
public class QuestionControllerMvc {

    @Autowired
    QuestionService questionService;

    @Autowired
    AnswerService answerService;

//    @GetMapping
//    public String displayRandomQuestion(HttpSession session) {
//        String sessionId = (String) session.getAttribute("sessionId");
//        int id = Integer.valueOf(sessionId);
//        List<QuestionDto> allQuestions = questionService.getAll();
//        Collections.shuffle(allQuestions);
//        session.setAttribute("allQuestions", allQuestions);
//        return "questions/random-question-page";
//    }

    @PostMapping("/display-question-card")
    public RedirectView handleDisplayQuestion(@RequestParam("question") String question, HttpSession session) {
        RedirectView redirectView = new RedirectView();
        redirectView.setContextRelative(true);
        List<String> allQuestions = (List<String>) session.getAttribute("allQuestions");
        QuestionDto currQuestion = questionService.getByValue(question);
        redirectView.setUrl("/display-question/" + currQuestion.getId());
        return redirectView;
    }

    @GetMapping("/display-question/{questionId}")
    public String displayQuestion(@PathVariable("questionId") String questionId, Model model, HttpServletRequest request) {
        String questionValue = questionService.getById(Integer.valueOf(questionId)).getQuestionValue();
        model.addAttribute("questionText", questionValue);
        return "questions/display-question-card";
    }

    @PostMapping("/save-in-favourites")
    public RedirectView saveInFavourites(@RequestParam("questionValue") String questionValue, RedirectAttributes redirectAttributes) {
        RedirectView redirectView = new RedirectView();
        redirectView.setContextRelative(true);
        //TODO
        redirectAttributes.addFlashAttribute("questionText", questionValue);
        redirectView.setUrl("/display-question/" + questionService.getByValue(questionValue).getId());
        return redirectView;
    }

    @PostMapping("/go-to-next-question")
    public RedirectView nextQuestion(@RequestParam("questionValue") String questionValue, HttpSession session, RedirectAttributes redirectAttributes) {
        RedirectView redirectView = new RedirectView();
        redirectView.setContextRelative(true);
        String currQuestionValue = questionService.getByValue(questionValue).getQuestionValue();
        List<String> previousQuestions;
        if(session.getAttribute("previousQuestions") == null) {
             previousQuestions = new ArrayList<>();
        }
        else {
             previousQuestions = (List<String>)session.getAttribute("previousQuestions");
        }
        previousQuestions.add(currQuestionValue);
        session.setAttribute("previousQuestions", previousQuestions);
        List<String> allQuestions = (List<String>) session.getAttribute("allQuestions");
        allQuestions.remove(currQuestionValue);
        if (allQuestions.size() == 0) {
            redirectView.setUrl("/no-more-questions");
            redirectAttributes.addFlashAttribute("previousQuestion", questionValue);
            return redirectView;
        }
        int nextQuestionId = questionService.getByValue(allQuestions.get(0)).getId();
        redirectView.setUrl("/display-question/" + String.valueOf(nextQuestionId));
        return redirectView;
    }

    @PostMapping("/go-to-previous-question")
    public RedirectView previousQuestion(@RequestParam("questionValue") String questionValue, HttpSession session) {
        RedirectView redirectView = new RedirectView();
        redirectView.setContextRelative(true);
        List<String> previousQuestions = (List<String>) session.getAttribute("previousQuestions");
        if (previousQuestions.size() == 0) {
            int questionId = questionService.getByValue(questionValue).getId();
            redirectView.setUrl("/display-question/" + questionId);
            return redirectView;
        }

        List<String> allQuestion =(List<String>) session.getAttribute("allQuestions");
        String currQuestion = questionService.getByValue(questionValue).getQuestionValue();
        if (!allQuestion.contains(currQuestion)) {
            allQuestion.add(currQuestion);
        }
        String previousQuestionValue = previousQuestions.get(previousQuestions.size() - 1);
        previousQuestions.remove(previousQuestionValue);
        session.setAttribute("previousQuestion", previousQuestions);
        int questionId = questionService.getByValue(previousQuestionValue).getId();
        redirectView.setUrl("/display-question/" + questionId);
        return redirectView;
    }

    @GetMapping("no-more-questions")
    public String noMoreQuestions() {
        return "questions/no-more-questions";
    }
}
