package com.haipiao.common.redis;

public class RedisConfig {

    private String hostname;
    private int port;
    private int dbIndex;
    private long defaultTTL;

    public String getHostname() {
        return hostname;
    }

    public RedisConfig setHostname(String hostname) {
        this.hostname = hostname;
        return this;
    }

    public int getPort() {
        return port;
    }

    public RedisConfig setPort(int port) {
        this.port = port;
        return this;
    }

    public int getDbIndex() {
        return dbIndex;
    }

    public RedisConfig setDbIndex(int dbIndex) {
        this.dbIndex = dbIndex;
        return this;
    }

    public long getDefaultTTL() {
        return defaultTTL;
    }

    public RedisConfig setDefaultTTL(long defaultTTL) {
        this.defaultTTL = defaultTTL;
        return this;
    }

}
