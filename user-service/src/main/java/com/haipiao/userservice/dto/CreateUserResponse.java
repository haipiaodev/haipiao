package com.haipiao.userservice.dto;

import com.google.gson.annotations.SerializedName;

public class CreateUserResponse {
    @SerializedName("success")
    private Boolean success;
    @SerializedName("error")
    private String error;
    @SerializedName("data")
    private Data data;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

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


