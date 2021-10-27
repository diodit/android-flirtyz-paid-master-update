package com.owo.phlurtyzpaid.model;

public class Flirty {
    private String icon;
    private String Name;
    private int price;

    public Flirty(String icon, String name, int price) {
        this.icon = icon;
        Name = name;
        this.price = price;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
