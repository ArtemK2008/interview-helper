package com.project.green.controller;

import com.project.green.entities.Topic;
import com.project.green.service.TopicService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/topic")
@Tag(name = "Topic Controller", description = "controller for operation by topic")
public class TopicController {

    private final TopicService service;

    @Autowired
    public TopicController(TopicService service) {
        this.service = service;
    }

    @Operation(summary = "Get all topics endpoint")
    @GetMapping("/all")
    public ResponseEntity<List<Topic>> getAllTopics() {
        List<Topic> topics = service.getAll();
        return ResponseEntity.ok(topics);
    }

    @Operation(
            summary = "Get topic by id endpoint",
            description = "Client sends information in JSON to get topic."
    )
    @GetMapping("/{id}")
    public ResponseEntity<Topic> getTopicById(@PathVariable("id") int id) {
        Topic topic = service.getTopicById(id);
        return ResponseEntity.ok(topic);
    }

    @Operation(
            summary = "Create topic endpoint",
            description = "Client sends information in JSON to save topic. " +
                    "Name have to be filled"
    )
    @PostMapping("/create")
    public ResponseEntity<Void> create(@RequestBody @Valid Topic topic) {
        service.save(topic);
        return ResponseEntity.ok().build();
    }

    @Operation(
            summary = "Update topic endpoint",
            description = "Client sends information in JSON to update name topic. " +
                    "Name have to be filled"
    )
    @PostMapping("/update")
    public ResponseEntity<Topic> update(@RequestBody @Valid Topic topic) {
        service.update(topic);
        return ResponseEntity.ok(topic);
    }

    @Operation(
            summary = "Delete topic by id endpoint",
            description = "Client sends information in JSON to delete topic."
    )
    @PostMapping("/delete")
    public ResponseEntity<Void> delete(@RequestBody @Valid Topic topic) {
        service.deleteById(topic.getId());
        return ResponseEntity.ok().build();
    }

}
