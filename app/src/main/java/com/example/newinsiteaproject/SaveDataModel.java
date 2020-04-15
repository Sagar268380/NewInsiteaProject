package com.example.newinsiteaproject;

import android.content.Intent;

public class SaveDataModel {

    String mobile,name,password,email;


    public SaveDataModel(String mobile, String name, String password, String email) {
        this.mobile = mobile;
        this.name = name;
        this.password = password;
        this.email = email;

    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
