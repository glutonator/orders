package com.orders;

public class Error {
    public int code ;
    public String message ;
    public String details ;
    public String url ;

    public Error() {
    }

    public Error(int code, String message, String details) {
        this.code = code;
        this.message = message;
        this.details = details;
        this.url = "test_url";
    }

    public Error(int code, String message, String details, String url) {
        this.code = code;
        this.message = message;
        this.details = details;
        this.url = url;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
