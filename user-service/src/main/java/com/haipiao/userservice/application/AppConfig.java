package com.haipiao.userservice.application;

import com.google.gson.Gson;
import com.haipiao.persist.config.PersistConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(PersistConfig.class)
@ComponentScan(basePackages = {"com.haipiao.userservice.handler"})
public class AppConfig {
    @Bean
    public Gson gson() {
        return new Gson();
    }
}
