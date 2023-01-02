package com.project.green;

import com.project.green.dao.QuestionDao;
import com.project.green.dao.TopicDao;
import com.project.green.dto.QuestionDto;
import com.project.green.entities.Question;
import com.project.green.entities.Topic;
import com.project.green.mapper.QuestionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class InterviewHelperApplication implements CommandLineRunner {
    @Autowired
    QuestionDao questionDao;

    @Autowired
    TopicDao topicDao;
    @Autowired
    private QuestionMapper questionMapper;


    public static void main(String[] args) {
        SpringApplication.run(InterviewHelperApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        List<Topic> all = topicDao.getAll();
        all.forEach(System.out::println);
    }
}
