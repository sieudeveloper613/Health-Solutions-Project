package com.example.healthsolutionsapplication.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Product {
    @SerializedName("Ids")
    @Expose
    private int ids;
    @SerializedName("categoryId")
    @Expose
    private int categoryId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("price")
    @Expose
    private double price;
    @SerializedName("categoryName")
    @Expose
    private String categoryName;
    @SerializedName("typeProduct")
    @Expose
    private String typeProduct;
    @SerializedName("whereProduct")
    @Expose
    private String whereProduct;
    @SerializedName("branchProduct")
    @Expose
    private String branchProduct;
    @SerializedName("image")
    @Expose
    private String image;

    public int getIds() {
        return ids;
    }

    public void setIds(int ids) {
        this.ids = ids;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getTypeProduct() {
        return typeProduct;
    }

    public void setTypeProduct(String typeProduct) {
        this.typeProduct = typeProduct;
    }

    public String getWhereProduct() {
        return whereProduct;
    }

    public void setWhereProduct(String whereProduct) {
        this.whereProduct = whereProduct;
    }

    public String getBranchProduct() {
        return branchProduct;
    }

    public void setBranchProduct(String branchProduct) {
        this.branchProduct = branchProduct;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
