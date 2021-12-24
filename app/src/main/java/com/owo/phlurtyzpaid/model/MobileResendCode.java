package com.owo.phlurtyzpaid.model;

public class MobileResendCode {
    private String email;

    public MobileResendCode(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}