package com.haipiao.registration.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, JacksonAutoConfiguration.class})
public class Application {

    public static void main(String[] args) {
        System.out.println(System.getProperty("spring.http.converters.preferred-json-mapper"));
        SpringApplication.run(Application.class, args);
    }

}
