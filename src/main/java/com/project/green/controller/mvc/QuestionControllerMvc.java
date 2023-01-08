package com.project.green.controller.mvc;

import com.project.green.dto.QuestionDto;
import com.project.green.mapper.QuestionMapper;
import com.project.green.service.AnswerService;
import com.project.green.service.PersonService;
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

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Scope("session")
public class QuestionControllerMvc {

    @Autowired
    QuestionService questionService;

    @Autowired
    AnswerService answerService;

    @Autowired
    PersonService personService;

    @Autowired
    QuestionMapper questionMapper;

    @Autowired
    StatisticsService statisticsService;

    @PostMapping("/display-question-card")
    public RedirectView handleDisplayQuestion(@RequestParam("question") String question) {
        RedirectView redirectView = new RedirectView();
        redirectView.setContextRelative(true);
        QuestionDto currQuestion = questionService.getByValue(question);
        redirectView.setUrl("/display-question/" + currQuestion.getId());
        return redirectView;
    }

    @GetMapping("/display-question/{questionId}")
    public String displayQuestion(@PathVariable("questionId") String questionId, Model model) {
        String questionValue = questionService.getById(Integer.parseInt(questionId)).getQuestionValue();
        model.addAttribute("questionText", questionValue);
        return "questions/display-question-card";
    }

    @PostMapping("/save-in-favourites")
    public RedirectView saveInFavourites(@RequestParam("questionValue") String questionValue, HttpSession session, RedirectAttributes redirectAttributes) {
        RedirectView redirectView = new RedirectView();
        redirectView.setContextRelative(true);
        String sessionId = (String) session.getAttribute("sessionId");
        //TODO
        //not working
        QuestionDto question = questionService.getByValue(questionValue);
        personService.addQuestionToFavourites(Integer.parseInt(sessionId), question);
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
        if (session.getAttribute("previousQuestions") == null) {
            previousQuestions = new ArrayList<>();
        } else {
            previousQuestions = (List<String>) session.getAttribute("previousQuestions");
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
        redirectView.setUrl("/display-question/" + nextQuestionId);
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

        List<String> allQuestion = (List<String>) session.getAttribute("allQuestions");
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

    @PostMapping("/mark-as-correct")
    public RedirectView answerWasCorrect(@RequestParam("questionValue") String questionValue, HttpSession session, RedirectAttributes redirectAttributes) {
        RedirectView redirectView = new RedirectView();
        redirectView.setContextRelative(true);
        String sessionId = (String) session.getAttribute("sessionId");

        Map<String, List<QuestionDto>> peopleAlreadyVotedForQuestion;
        if (session.getAttribute("ids of voted correct for question") == null) {
            peopleAlreadyVotedForQuestion = new HashMap<>();
        } else {
            peopleAlreadyVotedForQuestion = (Map<String, List<QuestionDto>>) session.getAttribute("ids of voted correct for question");
        }

        QuestionDto currQuestion = questionService.getByValue(questionValue);
        List<QuestionDto> questionDtos = peopleAlreadyVotedForQuestion.get(sessionId);
        if (questionDtos == null) {
            questionDtos = new ArrayList<>();
        }
        if (!peopleAlreadyVotedForQuestion.containsKey(sessionId)) {
            handleCorrectWhenQuestionWasNotVoted(questionValue, session, redirectAttributes, redirectView, sessionId, peopleAlreadyVotedForQuestion, currQuestion, questionDtos);
        } else if (!questionDtos.contains(currQuestion)) {
            handleCorrectWhenQuestionWasNotVoted(questionValue, session, redirectAttributes, redirectView, sessionId, peopleAlreadyVotedForQuestion, currQuestion, questionDtos);
        } else {
            redirectAttributes.addFlashAttribute("previousQuestion", questionValue);
            redirectView.setUrl("/already-voted");
        }
        return redirectView;
    }

    private void handleCorrectWhenQuestionWasNotVoted(String questionValue, HttpSession session, RedirectAttributes redirectAttributes, RedirectView redirectView, String sessionId, Map<String, List<QuestionDto>> peopleAlreadyVotedForQuestion, QuestionDto currQuestion, List<QuestionDto> questionDtos) {
        questionDtos.add(currQuestion);
        peopleAlreadyVotedForQuestion.put(sessionId, questionDtos);
        session.setAttribute("ids of voted correct for question", peopleAlreadyVotedForQuestion);
        statisticsService.incrementCorrects(Integer.parseInt(sessionId), 1);
        redirectAttributes.addFlashAttribute("previousQuestion", questionValue);
        redirectView.setUrl("congrats-for-correct");
    }

    @PostMapping("/mark-as-incorrect")
    public RedirectView answerWasWrong(@RequestParam("questionValue") String questionValue, HttpSession session, RedirectAttributes redirectAttributes) {
        RedirectView redirectView = new RedirectView();
        redirectView.setContextRelative(true);
        String sessionId = (String) session.getAttribute("sessionId");

        Map<String, List<QuestionDto>> peopleAlreadyVotedForQuestion;
        if (session.getAttribute("ids of voted wrong for question") == null) {
            peopleAlreadyVotedForQuestion = new HashMap<>();
        } else {
            peopleAlreadyVotedForQuestion = (Map<String, List<QuestionDto>>) session.getAttribute("ids of voted wrong for question");
        }

        QuestionDto currQuestion = questionService.getByValue(questionValue);
        List<QuestionDto> questionDtos = peopleAlreadyVotedForQuestion.get(sessionId);
        if (questionDtos == null) {
            questionDtos = new ArrayList<>();
        }
        if (!peopleAlreadyVotedForQuestion.containsKey(sessionId)) {
            handleIncorrectWhenQuestionWasNotVoted(questionValue, session, redirectAttributes, redirectView, sessionId, peopleAlreadyVotedForQuestion, currQuestion, questionDtos);
        } else if (!questionDtos.contains(currQuestion)) {
            handleIncorrectWhenQuestionWasNotVoted(questionValue, session, redirectAttributes, redirectView, sessionId, peopleAlreadyVotedForQuestion, currQuestion, questionDtos);
        } else {
            redirectAttributes.addFlashAttribute("previousQuestion", questionValue);
            redirectView.setUrl("/already-voted");
        }
        return redirectView;
    }

    private void handleIncorrectWhenQuestionWasNotVoted(@RequestParam("questionValue") String questionValue, HttpSession session, RedirectAttributes redirectAttributes, RedirectView redirectView, String sessionId, Map<String, List<QuestionDto>> peopleAlreadyVotedForQuestion, QuestionDto currQuestion, List<QuestionDto> questionDtos) {
        questionDtos.add(currQuestion);
        peopleAlreadyVotedForQuestion.put(sessionId, questionDtos);
        session.setAttribute("ids of voted wrong for question", peopleAlreadyVotedForQuestion);
        statisticsService.incrementIncorrect(Integer.parseInt(sessionId), 1);
        statisticsService.addQuestionToStatistics(Integer.parseInt(sessionId), currQuestion);
        redirectAttributes.addFlashAttribute("previousQuestion", questionValue);
        redirectView.setUrl("sorry-for-wrong");
    }

    @GetMapping("congrats-for-correct")
    public String answerCorrectCongratulation() {
        return "questions/correct";
    }

    @GetMapping("sorry-for-wrong")
    public String answerWrongCongratulation() {
        return "questions/wrong";
    }

    @GetMapping("/already-voted")
    public String alreadyVoted() {
        return "questions/already-voted";
    }

}
