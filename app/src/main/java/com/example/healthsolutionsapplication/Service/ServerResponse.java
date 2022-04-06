package com.example.healthsolutionsapplication.Service;

import com.example.healthsolutionsapplication.Model.Customer;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ServerResponse {
    @SerializedName("customer")
    @Expose
    private Customer customer;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("result")
    @Expose
    private Integer result;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }
}
