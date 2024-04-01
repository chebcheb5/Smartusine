package com.example.smartusine.model;

public class user {
    private String fullName,email,cin,phone,password;

    public user() {
    }
    public user(String fullName, String email, String cin, String phone, String password) {
        this.fullName = fullName;
        this.email = email;
        this.cin = cin;
        this.phone = phone;
        this.password = password;
    }
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getTel() {
        return phone;
    }

    public void setTel(String tel) {
        this.phone = tel;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}







