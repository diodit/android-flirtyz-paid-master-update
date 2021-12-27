package com.owo.phlurtyzpaid.api.models;

public class CreatedBy {
    private int id;
    private String email;
    private String firstName;
    private String lastName;
    private String stripeCustonerId;
    private String role;
    private String active;
    private String updatedAt;
    private String createdAt;
    private EmojiModel emojis;

    public CreatedBy() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStripeCustonerId() {
        return stripeCustonerId;
    }

    public void setStripeCustonerId(String stripeCustonerId) {
        this.stripeCustonerId = stripeCustonerId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public EmojiModel getEmojis() {
        return emojis;
    }

    public void setEmojis(EmojiModel emojis) {
        this.emojis = emojis;
    }
}
