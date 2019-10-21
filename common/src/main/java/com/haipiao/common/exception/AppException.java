package com.haipiao.common.exception;

import com.haipiao.common.enums.ErrorCode;

public class AppException extends Exception {

    private ErrorCode errorCode;

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    private AppException() {
        super();
    }

    public AppException(ErrorCode errorCode) {
        super();
        this.errorCode = errorCode;
    }

    public AppException(ErrorCode errorCode, Throwable cause) {
        super(cause);
        this.errorCode = errorCode;
    }

    public AppException(ErrorCode errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

}
