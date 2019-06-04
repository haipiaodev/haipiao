package com.haipiao.registration.application;

import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;
import com.haipiao.common.enums.SecurityCodeType;
import com.haipiao.common.redis.RedisClientWrapper;
import com.haipiao.common.redis.RedisConfig;
import com.haipiao.common.util.security.SecurityCodeConfig;
import com.haipiao.common.util.security.SecurityCodeManager;
import com.haipiao.persist.config.PersistConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(PersistConfig.class)
@ComponentScan(basePackages = {"com.haipiao.registration.handler"})
public class AppConfig {

    @Value("${redis.host:localhost}")
    private String host;
    @Value("${redis.port:16379}")
    private int port;
    @Value("${redis.dbIndex:0}")
    private int dbIndex;
    @Value("${redis.defaultTTL:600}")
    private long defaultTTL;

    @Bean
    public Gson gson() {
        return new Gson();
    }

    @Bean
    public RedisClientWrapper redisClient() {
        return new RedisClientWrapper(new RedisConfig()
            .setHostname(host)
            .setPort(port)
            .setDbIndex(dbIndex)
            .setDefaultTTL(defaultTTL));
    }

    @Bean
    public SecurityCodeManager securityCodeManager(Gson gson,
                                                   RedisClientWrapper redisClient) {
        return new SecurityCodeManager(gson, redisClient,
            ImmutableMap.of(
                SecurityCodeType.REGISTRATION, new SecurityCodeConfig(6, 6 * 60, 5 * 1000),
                SecurityCodeType.CHANGE_PASSWORD, new SecurityCodeConfig(6, 6 * 60, 5 * 1000),
                SecurityCodeType.LOGIN, new SecurityCodeConfig(6, 6 * 60, 5 * 1000)
            ));
    }

}


