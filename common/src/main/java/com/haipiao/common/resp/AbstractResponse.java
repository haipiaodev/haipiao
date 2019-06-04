package com.haipiao.common.resp;

import com.google.gson.annotations.SerializedName;
import com.haipiao.common.ErrorInfo;

import java.io.Serializable;

public abstract class AbstractResponse<T> implements Serializable {

    @SerializedName("success")
    private boolean success;

    @SerializedName("error_info")
    private ErrorInfo errorInfo;

    @SerializedName("data")
    private T data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public ErrorInfo getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(ErrorInfo errorInfo) {
        this.errorInfo = errorInfo;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
