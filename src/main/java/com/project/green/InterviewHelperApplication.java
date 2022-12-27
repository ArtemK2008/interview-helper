package com.project.green;

import com.project.green.repository.impl.DatabaseCleaner;
import com.project.green.repository.impl.StatisticsDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InterviewHelperApplication  {

  @Autowired
  DatabaseCleaner cleaner;

  @Autowired
  StatisticsDaoImpl statisticsDao;

  public static void main(String[] args) {
    SpringApplication.run(InterviewHelperApplication.class, args);
  }

  }

