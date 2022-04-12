package com.example.healthsolutionsapplication.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Address {
    private Customer customer;

    @SerializedName("_idAddress")
    @Expose
    private int idAddress;

    @SerializedName("_address")
    @Expose
    private String address;

    @SerializedName("_isDefault")
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean isDefault) {
        isDefault = isDefault;
    }
}
