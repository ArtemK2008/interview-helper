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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        request.getSession().setAttribute("lastQuestion", question);

        QuestionDto currQuestion = questionService.getByValue(question);
        int id = currQuestion.getId();
        AnswerDto bestAnswer;
        try {
             bestAnswer = answerService.getBestByQuestionId(id);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("lastQuestion", question);
            redirectView.setUrl("/answer/insert-your-answer-for-question");
            return redirectView;
        }
        String answerText = bestAnswer.getAnswerText();
        List<AnswerDto> allAnswers = answerService.getAllAnswersToQuestionInOrderByVoice(id);
        if (!allAnswers.isEmpty() && bestAnswer != null) {
            allAnswers.remove(bestAnswer);
        }
        request.getSession().setAttribute("answers", allAnswers);
        redirectAttributes.addFlashAttribute("question", question);
        redirectAttributes.addFlashAttribute("answer", answerText);

        redirectView.setUrl("/questions/display-answer/" + id);
        return redirectView;
    }

    @GetMapping("questions/display-answer/{questionId}")
    public String displayAnswer() {
        return "answer/answer-page-card";
    }

    @GetMapping("/Display-other-card-answers")
    public String displayOtherAnswwers(HttpServletRequest request, Model model) {
        List<AnswerDto> answers = (List<AnswerDto>) request.getSession().getAttribute("answers");
        request.getSession().removeAttribute("answers");
        String lastQuestion = (String)request.getSession().getAttribute("lastQuestion");
        model.addAttribute("answers", answers);
        model.addAttribute("lastQuestion", lastQuestion);
        return "/answer/show-other-possible-card-answers";
    }

    @PostMapping("/vote-for-answer-by-hundred")
    public RedirectView voteForAnswerByHundred(@RequestParam("answer") String answer,HttpSession session) {
        RedirectView redirectView = new RedirectView();
        redirectView.setContextRelative(true);
        AnswerDto currAnswer = answerService.getByValue(answer);
        String currQuestion = (String)session.getAttribute("lastQuestion");
        int questionId = questionService.getByValue(currQuestion).getId();
        answerService.incrementVoiceCount(currAnswer.getId(), 100);
        if(answerService.checkIfAnswersVoiceCountBiggerThenDefault(questionId,currAnswer)) {
            answerService.swapDefaultForNewOne(questionId,currAnswer);
        }
        redirectView.setUrl("/thanks-for-vote");
        return redirectView;
    }

    @PostMapping("/vote-for-answer")
    public RedirectView voteForAnswer(@RequestParam("answer") String answer, RedirectAttributes redirectAttributes, HttpSession session) {
        RedirectView redirectView = new RedirectView();
        redirectView.setContextRelative(true);

        String sessionId = (String) session.getAttribute("sessionId");

        Map<String, List<AnswerDto>> peopleAlreadyVotedForAnswer;
        if (session.getAttribute("ids of voted for answer") == null) {
            peopleAlreadyVotedForAnswer = new HashMap<>();
        } else {
            peopleAlreadyVotedForAnswer = (Map<String, List<AnswerDto>>) session.getAttribute("ids of voted for answer");
        }

        AnswerDto currAnswer = answerService.getByValue(answer);
        List<AnswerDto> answerDtos = peopleAlreadyVotedForAnswer.get(sessionId);
        if (answerDtos == null) {
            answerDtos = new ArrayList<>();
        }
        if (!peopleAlreadyVotedForAnswer.containsKey(sessionId)) {
            handleVoteWhenAnswerWasNotVoted(answer, session, redirectAttributes, redirectView, sessionId, peopleAlreadyVotedForAnswer, currAnswer, answerDtos);
        } else if (!answerDtos.stream().map(AnswerDto::getAnswerText).collect(Collectors.toList()).contains(currAnswer.getAnswerText())) {
            handleVoteWhenAnswerWasNotVoted(answer, session, redirectAttributes, redirectView, sessionId, peopleAlreadyVotedForAnswer, currAnswer, answerDtos);
        } else {
            redirectView.setUrl("/answer/already-voted");
        }
        return redirectView;
    }


    private void handleVoteWhenAnswerWasNotVoted(String answer, HttpSession session,
                                                 RedirectAttributes redirectAttributes, RedirectView redirectView,
                                                 String sessionId, Map<String, List<AnswerDto>> peopleAlreadyVotedForAnswer, AnswerDto currAnswer,
                                                 List<AnswerDto> answerDtos) {
        answerService.incrementVoiceCount(currAnswer.getId(), 1);
        String currQuestion = (String)session.getAttribute("lastQuestion");
        int questionId = questionService.getByValue(currQuestion).getId();
        if(answerService.checkIfAnswersVoiceCountBiggerThenDefault(questionId,currAnswer)) {
            answerService.swapDefaultForNewOne(questionId,currAnswer);
        }
        answerDtos.add(currAnswer);
        peopleAlreadyVotedForAnswer.put(sessionId, answerDtos);
        session.setAttribute("ids of voted for answer", peopleAlreadyVotedForAnswer);
        redirectView.setUrl("/thanks-for-vote");
    }

    @GetMapping("/thanks-for-vote")
    public String voteAccepted(Model model, HttpSession session) {
        model.addAttribute("lastQuestion", session.getAttribute("lastQuestion"));
        return "answer/vote-accepted";
    }

    @GetMapping("/answer/already-voted")
    public String alreadyVoted(Model model, HttpSession session) {
        model.addAttribute("lastQuestion", session.getAttribute("lastQuestion"));
        return "answer/already-voted";
    }

    @PostMapping("/propose-your-answer")
    public RedirectView handleProposeAnswer(@RequestParam("lastQuestion") String lastQuestion,
                                             RedirectAttributes redirectAttributes) {
        RedirectView redirectView = new RedirectView();
        redirectView.setContextRelative(true);
        redirectAttributes.addFlashAttribute("lastQuestion", lastQuestion);
        redirectView.setUrl("/answer/insert-your-answer-for-question");
        return redirectView;
    }

    @GetMapping("/answer/insert-your-answer-for-question")
    public String proposeAnswer() {
        return "answer/insert-your-answer-page";
    }

    @PostMapping("/insert-proposed-answer")
    public RedirectView handleProposedAnswerInsertion(@RequestParam("questionValue") String questionValue,
                                                      @RequestParam("answerText") String answerText,
                                                      RedirectAttributes redirectAttributes) {
        RedirectView redirectView = new RedirectView();
        redirectView.setContextRelative(true);
        QuestionDto currQuestion = questionService.getByValue(questionValue);

        if (questionService.addAnswerToQuestion(currQuestion.getId(), answerText)) {
            QuestionDto questionDto = questionService.getByValue(questionValue);
            try {
                answerService.getBestByQuestionId(questionDto.getId());
            } catch (Exception e) {
                AnswerDto currAnswer = answerService.getByValue(answerText);
                currAnswer.setDefault(true);
                answerService.updateAnswer(currAnswer);
            } finally {
                redirectView.setUrl("/answer/insert-was-successfull");
            }
        } else {
            redirectView.setUrl("/answer/insert-failed");
        }
        redirectAttributes.addFlashAttribute("questionValue", questionValue);
        return redirectView;
    }

    @GetMapping("/answer/insert-was-successfull")
    public String succsessfullAnswerInsert() {
        return "answer/answer-insert-completed";
    }

    @GetMapping("/answer/insert-failed")
    public String unsuccsessfullAnswerInsert() {
        return "answer/answer-insert-failed";
    }
}
