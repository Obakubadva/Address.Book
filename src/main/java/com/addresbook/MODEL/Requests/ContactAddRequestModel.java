package com.addresbook.MODEL.Requests;

public class ContactAddRequestModel {
    private String picture, name, address;

    public ContactAddRequestModel(String picture, String name, String address) {
        this.picture = picture;
        this.name = name;
        this.address = address;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
