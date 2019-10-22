package com.haipiao.common.util.session;

import java.util.StringJoiner;

/**
 * session object in Redis
 */
public class UserSessionInfo {

    private Integer userId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", UserSessionInfo.class.getSimpleName() + "[", "]")
            .add("userId=" + userId)
            .toString();
    }
}
