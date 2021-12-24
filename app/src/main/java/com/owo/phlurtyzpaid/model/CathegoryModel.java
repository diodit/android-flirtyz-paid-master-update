package com.owo.phlurtyzpaid.model;

public class CathegoryModel {
    private int id;
    private String file;
    private String image;
    private String name;
    private String version;

    private Created createdAt;

    private Updated updatedAt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Created getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Created createdAt) {
        this.createdAt = createdAt;
    }

    public Updated getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Updated updatedAt) {
        this.updatedAt = updatedAt;
    }
}
