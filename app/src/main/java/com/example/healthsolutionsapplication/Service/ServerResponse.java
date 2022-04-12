package com.example.healthsolutionsapplication.Service;

import com.example.healthsolutionsapplication.Model.Address;
import com.example.healthsolutionsapplication.Model.Customer;
import com.example.healthsolutionsapplication.Model.Product;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ServerResponse {
    @SerializedName("customer")
    @Expose
    private Customer customer;

    @SerializedName("product")
    @Expose
    private List<Product> product;

    @SerializedName("addressList")
    @Expose
    private List<Address> addressList = null;

    @SerializedName("address")
    @Expose
    private Address address;

    public Product getProduct1() {
        return product1;
    }

    public void setProduct1(Product product1) {
        this.product1 = product1;
    }

    @SerializedName("product1")
    @Expose
    private Product product1;

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("result")
    @Expose
    private Integer result;

    public List<Address> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<Address> addressList) {
        this.addressList = addressList;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Product> getProduct() {
        return product;
    }

    public void setProduct(List<Product> product) {
        this.product = product;
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
