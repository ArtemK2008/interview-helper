package com.project.green.controller.rest;

import com.project.green.dto.TopicDto;
import com.project.green.service.TopicService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/topic")
@Tag(name = "Topic Controller", description = "controller for operation by topic")
public class TopicController {

    @Autowired
    private TopicService topicService;

    @Operation(summary = "Get all topics endpoint")
    @GetMapping("/all")
    public List<TopicDto> getAllTopics() {
        return topicService.getAll();
    }

    @Operation(
            summary = "Get topic by id endpoint",
            description = "Client sends information in JSON to get topic."
    )
    @GetMapping("/{id}")
    public TopicDto getTopicById(@PathVariable("id") int id) {
        return topicService.getById(id);
    }

}
