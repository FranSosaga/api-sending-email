package com.email.send.exceptions;

import com.google.gson.annotations.Expose;
import org.springframework.http.HttpStatus;

public class ApiException extends RuntimeException {

    @Expose
    private String code;

    @Expose
    private String message;

    private HttpStatus status;

    private ErrorCode errorCode;

    private static final String GENERIC_ERROR_CODE = "internal_server_error";

    private static final ErrorCode DEFAULT_ERROR_CODE = ErrorCode.INTERNAL_ERROR;

    public ApiException(String message) {
        super(message);
        this.code = GENERIC_ERROR_CODE;
        this.message = message;
        this.status = HttpStatus.INTERNAL_SERVER_ERROR;
        this.errorCode = DEFAULT_ERROR_CODE;
    }

    public ApiException(String message, Throwable exception) {
        super(message, exception);
        this.code = GENERIC_ERROR_CODE;
        this.message = message;
        this.status = HttpStatus.INTERNAL_SERVER_ERROR;
        this.errorCode = DEFAULT_ERROR_CODE;
    }

    public ApiException(String code, String message, HttpStatus status) {
        super(message);
        this.code = code;
        this.message = message;
        this.status = status;
        this.errorCode = DEFAULT_ERROR_CODE;
    }

    public ApiException(String code, String message, ErrorCode errorCode) {
        super(message);
        this.code = code;
        this.message = message;
        this.errorCode = errorCode;
    }

    public ApiException(String code, String message, HttpStatus status, ErrorCode errorCode) {
        super(message);
        this.code = code;
        this.message = message;
        this.status = status;
        this.errorCode = errorCode;
    }

    public ApiException(String code, String message, ErrorCode errorCode, RuntimeException exception) {
        super(exception);
        this.code = code;
        this.message = message;
        this.errorCode = errorCode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

}
