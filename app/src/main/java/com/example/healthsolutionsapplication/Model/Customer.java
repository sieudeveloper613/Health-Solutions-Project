package com.example.healthsolutionsapplication.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Customer {
    @SerializedName("idCustomer")
    @Expose
    private int id;

    @SerializedName("nameCustomer")
    @Expose
    private String name;
    @SerializedName("accountCustomer")
    @Expose
    private String account;

    @SerializedName("passwordCustomer")
    @Expose
    private String password;

    @SerializedName("phoneCustomer")
    @Expose
    private String phone;

    @SerializedName("dobCustomer")
    @Expose
    private String dob;

    @SerializedName("emailCustomer")
    @Expose
    private String email;

    @SerializedName("genderCustomer")
    @Expose
    private int gender;

    @SerializedName("mainAddress")
    @Expose
    private String mainAddress;

    @SerializedName("avatarCustomer")
    @Expose
    private String avatar;

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

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getMainAddress() {
        return mainAddress;
    }

    public void setMainAddress(String mainAddress) {
        this.mainAddress = mainAddress;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
