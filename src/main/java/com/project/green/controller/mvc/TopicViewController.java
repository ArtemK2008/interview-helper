package com.project.green.controller.mvc;

import com.project.green.dto.QuestionDto;
import com.project.green.dto.TopicDto;
import com.project.green.service.QuestionService;
import com.project.green.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@Scope("session")
public class TopicViewController {

    @Autowired
    TopicService topicService;

    @Autowired
    QuestionService questionService;
    @GetMapping("/Display-Topics")
    public String goToTopics(HttpSession session, Model model) {
        model.addAttribute("id", session.getAttribute("sessionId"));
        List<TopicDto> allTopics = topicService.getAll();
        List<String> titles = allTopics.stream().map(TopicDto::getTitle).collect(Collectors.toList());
        model.addAttribute("topicList", titles);
        return "topics/topic-page";
    }

    @PostMapping("/display-topics-content")
    public RedirectView displayTopicsQuestions(@RequestParam("topic") String topic) {
        RedirectView redirectView = new RedirectView();
        redirectView.setContextRelative(true);
        TopicDto pickedTopic = topicService.getTopicByTitle(topic);
        Set<TopicDto> children = pickedTopic.getChildren();
        if (children.isEmpty()) {
            redirectView.setUrl("/topics/" + pickedTopic.getTitle());
        }
        else {
            redirectView.setUrl("/topics/display-sub-topics/" + pickedTopic.getTitle());
        }
        return redirectView;
    }

    @GetMapping("/topics/display-sub-topics/{title}")
    public String displaySubTopicList(@PathVariable("title") String title, Model model) {
        TopicDto pickedTopic = topicService.getTopicByTitle(title);
        List<String> childTitles = pickedTopic.getChildren().stream().map(TopicDto::getTitle).collect(Collectors.toList());
        model.addAttribute("subTopics", childTitles);
        return "topics/all-subtopics-for-picked-topic";
    }

    @PostMapping("/display-sub-topics-content")
    public  RedirectView displaySubTopicsQuestions(@RequestParam("topic") String topic) {
        RedirectView redirectView = new RedirectView();
        redirectView.setContextRelative(true);
        TopicDto pickedTopic = topicService.getTopicByTitle(topic);
        Set<TopicDto> children = pickedTopic.getChildren();
        if (children.isEmpty()) {
            redirectView.setUrl("/topics/" + pickedTopic.getTitle());
            return redirectView;
        }
        redirectView.setUrl("/topics/display-sub-topics/" + pickedTopic.getTitle());
        return redirectView;
    }

    @GetMapping("/topics/{title}")
    public String displayQuestionList(@PathVariable("title") String title, Model model,HttpSession session) {
        model.addAttribute("topic", title);
        int topicId = topicService.getTopicByTitle(title).getId();
        List<QuestionDto> questionList = questionService.findAllByTopicId(topicId);
        List<String> questionValues = questionList.stream().map(q -> q.getQuestionValue()).collect(Collectors.toList());
        model.addAttribute("allQuestions", questionValues);
        session.setAttribute("allQuestions", questionValues);
        return "topics/all-for-picked-topic";
    }

}
