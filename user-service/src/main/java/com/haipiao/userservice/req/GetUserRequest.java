package com.haipiao.userservice.req;

import com.google.gson.annotations.SerializedName;
import com.haipiao.common.req.AbstractRequest;

public class GetUserRequest extends AbstractRequest {
    @SerializedName("id")
    private Integer id;

    public Integer getId() {
        return id;
    }

    public GetUserRequest setId(int id) {
        this.id = id;
        return this;
    }
}
