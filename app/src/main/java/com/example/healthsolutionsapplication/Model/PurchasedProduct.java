package com.example.healthsolutionsapplication.Model;

public class PurchasedProduct {
        private int idPurchasedProduct;
        private int imagePurchasedProduct;
        private String namePurchasedProduct;
        private String statusPurchasedProduct;

    public PurchasedProduct(int idPurchasedProduct, int imagePurchasedProduct, String namePurchasedProduct, String statusPurchasedProduct) {
        this.idPurchasedProduct = idPurchasedProduct;
        this.imagePurchasedProduct = imagePurchasedProduct;
        this.namePurchasedProduct = namePurchasedProduct;
        this.statusPurchasedProduct = statusPurchasedProduct;
    }

   public PurchasedProduct(){}

    public int getIdPurchasedProduct() {
        return idPurchasedProduct;
    }

    public void setIdPurchasedProduct(int idPurchasedProduct) {
        this.idPurchasedProduct = idPurchasedProduct;
    }

    public int getImagePurchasedProduct() {
        return imagePurchasedProduct;
    }

    public void setImagePurchasedProduct(int imagePurchasedProduct) {
        this.imagePurchasedProduct = imagePurchasedProduct;
    }

    public String getNamePurchasedProduct() {
        return namePurchasedProduct;
    }

    public void setNamePurchasedProduct(String namePurchasedProduct) {
        this.namePurchasedProduct = namePurchasedProduct;
    }

    public String getStatusPurchasedProduct() {
        return statusPurchasedProduct;
    }

    public void setStatusPurchasedProduct(String statusPurchasedProduct) {
        this.statusPurchasedProduct = statusPurchasedProduct;
    }
}

