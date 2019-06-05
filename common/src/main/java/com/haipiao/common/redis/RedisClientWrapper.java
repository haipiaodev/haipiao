package com.haipiao.common.redis;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisFuture;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.async.RedisAsyncCommands;

import java.util.concurrent.TimeUnit;

public class RedisClientWrapper {

    private final RedisClient redisClient;
    private final long defaultTTL;
    private final StatefulRedisConnection<String, String> statefulConnection;

    public RedisClientWrapper(RedisConfig config) {
        this.redisClient = RedisClient.create(
            RedisURI.builder()
                .redis(config.getHostname(), config.getPort())
                .withDatabase(config.getDbIndex())
                .build());
        statefulConnection = redisClient.connect();
        defaultTTL = config.getDefaultTTL();
    }

    public String get(String key) {
        RedisAsyncCommands<String, String> commands = statefulConnection.async();
        return eval(commands.get(key));
    }

    public void set(String key, String val) {
        RedisAsyncCommands<String, String> commands = statefulConnection.async();
        eval(commands.setex(key, defaultTTL, val));
    }

    public void set(String key, String val, long ttl) {
        RedisAsyncCommands<String, String> commands = statefulConnection.async();
        eval(commands.setex(key, ttl, val));
    }

    public void delete(String key) {
        RedisAsyncCommands<String, String> commands = statefulConnection.async();
        eval(commands.del(key));
    }

    private <T> T eval(RedisFuture<T> cs) {
        try {
            return cs.get(5, TimeUnit.SECONDS);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

}
