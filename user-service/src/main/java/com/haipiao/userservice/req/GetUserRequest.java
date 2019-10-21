package com.haipiao.userservice.req;

import com.google.gson.annotations.SerializedName;

public class GetUserRequest {
    @SerializedName("id")
    private int id;

    public int getId() {
        return id;
    }

    public GetUserRequest setId(int id) {
        this.id = id;
        return this;
    }
}
