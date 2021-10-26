package com.owo.phlurtyzpaid.model;

public class Emoji {

    private String icon;
    private String groupName;
    private int price;

    public Emoji(String icon, String groupName, int price) {
        this.icon = icon;
        this.groupName = groupName;
        this.price = price;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}

