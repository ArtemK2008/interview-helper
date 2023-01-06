package com.project.green.controller.mvc;

import com.project.green.dto.AnswerDto;
import com.project.green.dto.QuestionDto;
import com.project.green.dto.TopicDto;
import com.project.green.service.AnswerService;
import com.project.green.service.QuestionService;
import com.project.green.service.StatisticsService;
import com.project.green.service.TopicService;
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
public class LoginController {

    @Autowired
    TopicService topicService;

    @Autowired
    StatisticsService statisticsService;

    @Autowired
    QuestionService questionService;

    @Autowired
    AnswerService answerService;


    @GetMapping("/index")
    public String showSignUpForm(Model model) {
        return "login/index";
    }

    @PostMapping("/index")
    public RedirectView handleLogin(@RequestParam("idFromIndex") String personId, HttpServletRequest request) {
        request.getSession().setAttribute("sessionId", personId);
        RedirectView redirectView = new RedirectView();
        redirectView.setContextRelative(true);
        redirectView.setUrl("/person/" + personId);
        return redirectView;
    }

    @GetMapping("/person/{personId}")
    public String personPage(@PathVariable("personId") String personId, Model model) {
        model.addAttribute("id", personId);
        return "login/personPage";

    }

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
        return "login/profile-page";
    }

    @PostMapping("/display-ununswered-questions")
    public RedirectView displayUnunswereedQuestion(@RequestParam("question") String question, RedirectAttributes redirectAttributes) {
        RedirectView redirectView = new RedirectView();
        redirectView.setContextRelative(true);
        QuestionDto currQuestion = questionService.getByValue(question);
        int id = currQuestion.getId();
        AnswerDto answerClass = answerService.getByQuestionId(id);
        String answerText = answerClass.getAnswerText();
        redirectAttributes.addFlashAttribute("question", question);
        redirectAttributes.addFlashAttribute("answer", answerText);
        redirectView.setUrl("/questions/display-answer/" + id);
        return redirectView;
    }

    @GetMapping("questions/display-answer/{questionId}")
    public String displayAnswer(@PathVariable("questionId") String questionId) {
        return "questions/answer-page";
    }

    @GetMapping("/Display-other-answers")
    public String displayOtherAnswwers() {
        return "/not-implemented";
    }


    @GetMapping("/Display-Topics")
    public String goToTopics(HttpSession session, Model model) {
        model.addAttribute("id", session.getAttribute("sessionId"));
        List<TopicDto> allTopics = topicService.getAll();
        List<String> titles = allTopics.stream().map(a -> a.getTitle()).collect(Collectors.toList());
        model.addAttribute("topicList", titles);
        return "questions/topic-page";
    }

    @PostMapping("/display-topics-content")
    public RedirectView displayTopicsQuestions(@RequestParam("topic") String topic, RedirectAttributes redirectAttributes) {
        RedirectView redirectView = new RedirectView();
        redirectView.setContextRelative(true);
        TopicDto pickedTopic = topicService.getTopicByTitle(topic);
        redirectAttributes.addFlashAttribute("topic", pickedTopic.getTitle());
        redirectView.setUrl("/questions/" + pickedTopic.getTitle());
        return redirectView;
    }

    @GetMapping("/questions/{title}")
    public String displayQuestionList(@PathVariable("title") String title, Model model) {
        return "questions/all-for-picked-topic";
    }


}
