package com.example.healthsolutionsapplication.Model;

public class User {
    String userName;
    String passWord;
    String nPhone;
    String email;
    String addRess;

    public User() {
    }

    public User(String userName, String passWord, String nPhone, String email, String addRess) {
        this.userName = userName;
        this.passWord = passWord;
        this.nPhone = nPhone;
        this.email = email;
        this.addRess = addRess;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getnPhone() {
        return nPhone;
    }

    public void setnPhone(String nPhone) {
        this.nPhone = nPhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddRess() {
        return addRess;
    }

    public void setAddRess(String addRess) {
        this.addRess = addRess;
    }
}
