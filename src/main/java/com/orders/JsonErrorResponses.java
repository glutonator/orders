package com.orders;

public class JsonErrorResponses<T> {
    private int StatusCode;
    private T Result;
    private boolean Success;
    private Error MessageContainer;

    public JsonErrorResponses(int statusCode, T result, boolean success, Error messageContainer) {
        StatusCode = statusCode;
        Result = result;
        Success = success;
        MessageContainer = messageContainer;
    }

    public int getStatusCode() {
        return StatusCode;
    }

    public void setStatusCode(int statusCode) {
        StatusCode = statusCode;
    }

    public T getResult() {
        return Result;
    }

    public void setResult(T result) {
        Result = result;
    }

    public boolean isSuccess() {
        return Success;
    }

    public void setSuccess(boolean success) {
        Success = success;
    }

    public Error getMessageContainer() {
        return MessageContainer;
    }

    public void setMessageContainer(Error messageContainer) {
        MessageContainer = messageContainer;
    }
}


