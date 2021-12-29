package com.owo.phlurtyzpaid.api.models;

import java.util.List;

public class UserStatus{
    private int id;
    private String name;
    private int price;
    private String file;
    private String image;
    public UpdatedAt updatedAt;
    public CreatedAt createdAt;
    private DeletedAt deletedAt;
    private String status;
    private Cathegory category;
    private CreatedBy createdBy;
    private List<EmojiModel> emojis;



    public UserStatus( ) {
    }

    public DeletedAt getDeletedAt() {
        return deletedAt;
    }

    public List<EmojiModel> getEmojis() {
        return emojis;
    }

    public void setEmojis(List<EmojiModel> emojis) {
        this.emojis = emojis;
    }

    public void setDeletedAt(DeletedAt deletedAt) {
        this.deletedAt = deletedAt;
    }

    public CreatedBy getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(CreatedBy createdBy) {
        this.createdBy = createdBy;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
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

    public UpdatedAt getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(UpdatedAt updatedAt) {
        this.updatedAt = updatedAt;
    }

    public CreatedAt getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(CreatedAt createdAt) {
        this.createdAt = createdAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Cathegory getCategory() {
        return category;
    }

    public void setCategory(Cathegory category) {
        this.category = category;
    }
}
