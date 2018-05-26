package com.orders;

public class Error {
    private int Code ;
    private String Message ;
    private String Details ;
    private String Url ;


    public Error(int code, String message, String details) {
        Code = code;
        Message = message;
        Details = details;
        Url = "test_url";
    }

    public Error(int code, String message, String details, String url) {
        Code = code;
        Message = message;
        Details = details;
        Url = url;
    }

    public int getCode() {
        return Code;
    }

    public void setCode(int code) {
        Code = code;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getDetails() {
        return Details;
    }

    public void setDetails(String details) {
        Details = details;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }
}
