package com.example.healthsolutionsapplication.Model;

public class Product {
    private int image;
    private String name;
    private int price;

    public Product(int image, String name, int price) {
        this.image = image;
        this.name = name;
        this.price = price;
    }

    public Product() {
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
