package com.haipiao.common.req;

import java.io.Serializable;

public abstract class AbstractRequest implements Serializable  {

    private Integer loggedInUserId;

    public Integer getLoggedInUserId() {
        return loggedInUserId;
    }

    public void setLoggedInUserId(Integer loggedInUserId) {
        this.loggedInUserId = loggedInUserId;
    }
}
