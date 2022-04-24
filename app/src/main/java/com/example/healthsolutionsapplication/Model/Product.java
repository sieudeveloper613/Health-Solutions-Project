package com.example.healthsolutionsapplication.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Product {
    @SerializedName("idProduct")
    @Expose
    private int idProduct;

    @SerializedName("idCategory")
    @Expose
    private int idCategory;

    @SerializedName("nameProduct")
    @Expose
    private String nameProduct;

    @SerializedName("priceProduct")
    @Expose
    private double priceProduct;

    @SerializedName("nameCategory")
    @Expose
    private String nameCategory;

    @SerializedName("nameType")
    @Expose
    private String typeProduct;

    @SerializedName("originProduct")
    @Expose
    private String originProduct;

    @SerializedName("branchProduct")
    @Expose
    private String branchProduct;

    @SerializedName("imageProduct")
    @Expose
    private String imageProduct;

    @SerializedName("contentProduct")
    @Expose
    private String contentProduct;

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public double getPriceProduct() {
        return priceProduct;
    }

    public void setPriceProduct(double priceProduct) {
        this.priceProduct = priceProduct;
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }

    public String getTypeProduct() {
        return typeProduct;
    }

    public void setTypeProduct(String typeProduct) {
        this.typeProduct = typeProduct;
    }

    public String getOriginProduct() {
        return originProduct;
    }

    public void setOriginProduct(String originProduct) {
        this.originProduct = originProduct;
    }

    public String getBranchProduct() {
        return branchProduct;
    }

    public void setBranchProduct(String branchProduct) {
        this.branchProduct = branchProduct;
    }

    public String getImageProduct() {
        return imageProduct;
    }

    public void setImageProduct(String imageProduct) {
        this.imageProduct = imageProduct;
    }

    public String getContentProduct() {
        return contentProduct;
    }

    public void setContentProduct(String contentProduct) {
        this.contentProduct = contentProduct;
    }
}
