package com.orders;

public class JsonErrorResponses<T> {
    public int statusCode;
    public T result;
    public boolean success;
    public Error errorContainer ;

    public JsonErrorResponses() {

    }

    public JsonErrorResponses(int statusCode, T result, boolean success, Error errorContainer) {
        this.statusCode = statusCode;
        this.result = result;
        this.success = success;
        this.errorContainer = errorContainer;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Error getErrorContainer() {
        return errorContainer;
    }

    public void setErrorContainer(Error errorContainer) {
        this.errorContainer = errorContainer;
    }



}


