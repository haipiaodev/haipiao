package com.haipiao.common.redis;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import io.lettuce.core.protocol.RedisCommand;

public class RedisClientWrapper {

    private final RedisClient redisClient;
    private final long defaultTTL;
    private final StatefulRedisConnection<String, String> statefulConnection;

    public RedisClientWrapper(RedisConfig config) {
        this.redisClient = RedisClient.create(RedisURI.builder()
            .redis(config.getHostname(), config.getPort())
            .withDatabase(config.getDbIndex())
            .build());
        statefulConnection = redisClient.connect();
        defaultTTL = config.getDefaultTTL();
    }

    public String get(String key) {
        RedisCommands<String, String> commands = statefulConnection.sync();
        return commands.get(key);
    }

    public void set(String key, String val) {
        RedisCommands<String, String> commands = statefulConnection.sync();
        commands.set(key, val);
        commands.expire(key, defaultTTL);
    }

    public void set(String key, String val, long ttl) {
        RedisCommands<String, String> commands = statefulConnection.sync();
        commands.set(key, val);
        commands.expire(key, ttl);
    }

    public void delete(String key) {
        RedisCommands<String, String> commands = statefulConnection.sync();
        commands.del(key);
    }


}
