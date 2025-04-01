package com.PharmaAssistant.exception;

public class PharmaException extends RuntimeException {
    private String errorCode;

    public PharmaException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
