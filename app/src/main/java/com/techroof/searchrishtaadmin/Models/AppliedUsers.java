package com.techroof.searchrishtaadmin.Models;

public class AppliedUsers {

    private String Status;
    private String Uid;
    private String image;
    private String name;

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getUid() {
        return Uid;
    }

    public void setUid(String uid) {
        Uid = uid;
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

    public AppliedUsers() {
    }

    public AppliedUsers(String status, String uid, String image, String name) {
        Status = status;
        Uid = uid;
        this.image = image;
        this.name = name;
    }



}
