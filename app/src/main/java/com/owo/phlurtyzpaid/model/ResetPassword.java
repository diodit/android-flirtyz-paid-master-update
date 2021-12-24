package com.owo.phlurtyzpaid.model;

public class ResetPassword {
    private String email;

    public ResetPassword(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
