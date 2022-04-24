package com.example.healthsolutionsapplication.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Address {
    @SerializedName("idAddress")
    @Expose
    private int idAddress;

    @SerializedName("nameReceiver")
    @Expose
    private String nameReceiver;

    @SerializedName("phoneReceiver")
    @Expose
    private String phoneReceiver;

    @SerializedName("contentAddress")
    @Expose
    private String contentAddress;

    @SerializedName("isDefault")
    @Expose
    private boolean isDefault;

    public int getIdAddress() {
        return idAddress;
    }

    public void setIdAddress(int idAddress) {
        this.idAddress = idAddress;
    }

    public String getNameReceiver() {
        return nameReceiver;
    }

    public void setNameReceiver(String nameReceiver) {
        this.nameReceiver = nameReceiver;
    }

    public String getPhoneReceiver() {
        return phoneReceiver;
    }

    public void setPhoneReceiver(String phoneReceiver) {
        this.phoneReceiver = phoneReceiver;
    }

    public String getContentAddress() {
        return contentAddress;
    }

    public void setContentAddress(String contentAddress) {
        this.contentAddress = contentAddress;
    }

    public boolean isDefault() {
        return isDefault;
    }
}
