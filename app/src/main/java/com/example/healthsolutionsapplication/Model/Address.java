package com.example.healthsolutionsapplication.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Address {
    private Customer customer;

    @SerializedName("idAddress")
    @Expose
    private int idAddress;

    @SerializedName("contentAddress")
    @Expose
    private String contentAddress;

    @SerializedName("isDefault")
    @Expose
    private boolean isDefault;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getIdAddress() {
        return idAddress;
    }

    public void setIdAddress(int idAddress) {
        this.idAddress = idAddress;
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
