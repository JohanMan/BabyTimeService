package com.johan.common.entity;

public class GlobalException extends RuntimeException {

    private int errorCode;
    private String errorMessage;

    public GlobalException(String errorMessage) {
        this.errorCode = ResultBody.CODE_ERROR;
        this.errorMessage = errorMessage;
    }

    public GlobalException(int errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}
