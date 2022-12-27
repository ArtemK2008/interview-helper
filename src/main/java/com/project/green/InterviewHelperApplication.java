package com.project.green;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.project.green")
public class InterviewHelperApplication {

  public static void main(String[] args) {
    SpringApplication.run(InterviewHelperApplication.class, args);

  }

}
