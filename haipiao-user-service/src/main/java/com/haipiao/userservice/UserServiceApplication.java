package com.haipiao.userservice;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


@SpringBootApplication
public class UserServiceApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        new UserServiceApplication()
                .configure(new SpringApplicationBuilder(UserServiceApplication.class))
                .run(args);
    }

}

