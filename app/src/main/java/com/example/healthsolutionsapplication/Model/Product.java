package com.example.healthsolutionsapplication.Model;

public class Product {
    private int categoryId,image;
    private String name;
    private int price;

    public Product(int image, String name, int price) {
        this.categoryId = categoryId;
        this.image = image;
        this.name = name;
        this.price = price;
    }

    public Product() {
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
