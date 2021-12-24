package com.owo.phlurtyzpaid.model;

public class RegisterUser {
    private String first_name;
    private String last_name;
    private String email;
    private String updated_at;
    private String created_at;
    private int id;

    public RegisterUser(String first_name, String last_name, String email, String updated_at, String created_at, int id) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.updated_at = updated_at;
        this.created_at = created_at;
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
