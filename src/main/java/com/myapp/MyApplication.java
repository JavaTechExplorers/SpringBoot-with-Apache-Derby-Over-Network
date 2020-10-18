package com.myapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(value = "com.myapp")
@EnableAutoConfiguration

/*
 * The @EnableJpaRepositories annotation activates Spring Data JPA. Spring Data JPA will create a concrete
 * implementation of the TaskRepository and configure it to talk to a back end in-memory database using JPA.
 */
@EnableJpaRepositories
@SpringBootApplication
public class MyApplication {

    public static void main(String[] args) {
	SpringApplication.run(MyApplication.class, args);
    }
}