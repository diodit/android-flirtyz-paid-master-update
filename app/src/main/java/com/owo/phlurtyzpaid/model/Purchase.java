package com.owo.phlurtyzpaid.model;

public class Purchase {
    private String Icon;
    private String Name;

    public Purchase(String icon, String name) {
        Icon = icon;
        Name = name;
    }

    public String getIcon() {
        return Icon;
    }

    public void setIcon(String icon) {
        Icon = icon;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
