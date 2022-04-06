package com.example.healthsolutionsapplication.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ServerResponse {
    @SerializedName("product")
    @Expose
    private List<Product> product = null;
    @SerializedName("result")
    @Expose
    private String result;

    public List<Product> getProduct() {
        return product;
    }

    public void setProduct(List<Product> product) {
        this.product = product;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
    }


