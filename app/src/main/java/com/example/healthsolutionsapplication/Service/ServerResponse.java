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

    @SerializedName("productList")
    @Expose
    private List<Product> productList;

    @SerializedName("addressList")
    @Expose
    private List<Address> addressList = null;

    @SerializedName("address")
    @Expose
    private Address address;

    @SerializedName("product")
    @Expose
    private Product product;

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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
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
