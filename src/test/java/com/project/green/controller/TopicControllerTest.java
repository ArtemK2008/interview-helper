package com.project.green.controller;

import com.project.green.dto.TopicDto;
import com.project.green.service.TopicService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TopicController.class)
@RunWith(SpringRunner.class)
public class TopicControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    TopicService topicService;

    @Test
    public void getAllTopics() throws Exception {
        List<TopicDto> topics = new ArrayList<>();
        Set<TopicDto> children = new HashSet<>();
        TopicDto topic1 = new TopicDto();
        topic1.setId(1);
        topic1.setTitle("Exceptions");
        children.add(topic1);
        TopicDto topic2 = new TopicDto();
        topic2.setId(2);
        topic2.setTitle("Spring");
        topic2.setChildren(children);
        TopicDto topic3 = new TopicDto();
        topic3.setId(3);
        topic3.setTitle("Core");
        topics.add(topic2);
        topics.add(topic3);
        when(topicService.getAll()).thenReturn(topics);
        mockMvc.perform(get("/topic/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[\n" +
                        "  {\n" +
                        "    \"id\": 2,\n" +
                        "    \"title\": \"Spring\",\n" +
                        "    \"children\": [\n" +
                        "      {\n" +
                        "        \"id\": 1,\n" +
                        "        \"title\": \"Exceptions\"\n" +
                        "      }\n" +
                        "    ]\n" +
                        "  },\n" +
                        "  {\n" +
                        "    \"id\": 3,\n" +
                        "    \"title\": \"Core\"\n" +
                        "  }\n" +
                        "]"));
    }

    @Test
    public void getTopicById() throws Exception {
        Set<TopicDto> children = new HashSet<>();
        TopicDto topic1 = new TopicDto();
        topic1.setId(1);
        topic1.setTitle("Exceptions");
        children.add(topic1);
        TopicDto topic2 = new TopicDto();
        topic2.setId(2);
        topic2.setTitle("Spring");
        topic2.setChildren(children);
        when(topicService.getById(2)).thenReturn(topic2);
        mockMvc.perform(get("/topic/2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "  \"id\" : 2\n" +
                                "}"))
                .andExpect(status().isOk())
                .andExpect(content().json(
                        "{\n" +
                                "  \"id\": 2,\n" +
                                "  \"title\": \"Spring\",\n" +
                                "  \"children\": [\n" +
                                "    {\n" +
                                "      \"id\": 1,\n" +
                                "      \"title\": \"Exceptions\"\n" +
                                "    }\n" +
                                "  ]\n" +
                                "}\n"));
    }

    @Test
    public void create() throws Exception {
        mockMvc.perform(post("/topic/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "  \"title\" : \"Spring\",\n" +
                                "  \"children\" : [\n" +
                                "    {\n" +
                                "      \"title\" : \"Core\"\n" +
                                "    }\n" +
                                "  ]\n" +
                                "}"))
                .andExpect(status().isOk());
    }

    @Test
    public void update() throws Exception {
        TopicDto topic1 = new TopicDto();
        topic1.setId(1);
        topic1.setTitle("Spring");
        when(topicService.update(topic1)).thenReturn(topic1);
        mockMvc.perform(post("/topic/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "  \"id\" : 1,\n" +
                                "  \"title\" : \"Spring\"\n" +
                                "}"))
                .andExpect(status().isOk())
                .andExpect(content().json(
                        "{\n" +
                                "  \"id\": 1,\n" +
                                "  \"title\": \"Spring\"\n" +
                                "}\n"));
    }

    @Test
    public void delete() throws Exception {
        mockMvc.perform(post("/topic/delete")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "  \"id\" : 2,\n" +
                                "  \"title\" : \"Spring\"\n" +
                                "}"))
                .andExpect(status().isOk());
    }

}
