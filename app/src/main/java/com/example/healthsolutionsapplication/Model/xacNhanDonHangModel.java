package com.example.healthsolutionsapplication.Model;

public class xacNhanDonHangModel {
    private int image;
    private String name, price, amount;

    public xacNhanDonHangModel(int image, String name, String price, String amount) {
        this.image = image;
        this.name = name;
        this.price = price;
        this.amount = amount;
    }

    public xacNhanDonHangModel() {
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
