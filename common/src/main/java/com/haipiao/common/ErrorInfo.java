package com.haipiao.common;

import com.google.gson.annotations.SerializedName;
import com.haipiao.common.enums.ErrorCode;

import java.io.Serializable;

public class ErrorInfo implements Serializable {

    @SerializedName("error_code")
    private final ErrorCode errorCode;

    @SerializedName("message")
    private final String message;

    public ErrorInfo(ErrorCode errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    @Override
    public String toString() {
        return "ErrorInfo{" +
            "errorCode=" + errorCode +
            ", message='" + message + '\'' +
            '}';
    }
}
