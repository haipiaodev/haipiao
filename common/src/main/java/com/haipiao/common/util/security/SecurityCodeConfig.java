package com.haipiao.common.util.security;

public class SecurityCodeConfig {

    private final int length;
    private final long ttl;
    private final long minWait;

    public SecurityCodeConfig(int length, long ttl, long minWait) {
        this.length = length;
        this.ttl = ttl;
        this.minWait = minWait;
    }

    public int getLength() {
        return length;
    }

    public long getTtl() {
        return ttl;
    }

    public long getMinWait() {
        return minWait;
    }
}
