package com.haipiao.userservice.resp;

import com.google.gson.annotations.SerializedName;
import com.haipiao.common.resp.AbstractResponse;

public class CreateUserResponse extends AbstractResponse<CreateUserResponse.Data> {

    public static class Data {
        @SerializedName("id")
        private int id;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }

}
