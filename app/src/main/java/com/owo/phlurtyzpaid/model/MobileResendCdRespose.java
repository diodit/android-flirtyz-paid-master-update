package com.owo.phlurtyzpaid.model;

public class MobileResendCdRespose {
    private String status;
    private String message;
    private String messages;

    public MobileResendCdRespose(String status, String message, String messages) {
        this.status = status;
        this.message = message;
        this.messages = messages;
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

    public String getMessages() {
        return messages;
    }

    public void setMessages(String messages) {
        this.messages = messages;
    }
}
