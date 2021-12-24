package com.owo.phlurtyzpaid.model;

public class RegisterResponse {
    private String status;
    private String message;


    private RegisterUser user;

    public RegisterResponse(){

    }
    public RegisterResponse(String status, String message, RegisterUser user) {
        this.status = status;
        this.message = message;
        this.user = user;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public RegisterUser getUser() {
        return user;
    }

    public void setUser(RegisterUser user) {
        this.user = user;
    }
}
