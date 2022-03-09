package com.example.healthsolutionsapplication.Model;

public class diaChiNhanHangModel{
    private String name, tel;
    private int image;
    private String address, defaultAddress;

    public diaChiNhanHangModel(String name, String tel, int image, String address, String defaultAddress) {
        this.name = name;
        this.tel = tel;
        this.image = image;
        this.address = address;
        this.defaultAddress = defaultAddress;
    }

    public diaChiNhanHangModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDefaultAddress() {
        return defaultAddress;
    }

    public void setDefaultAddress(String defaultAddress) {
        this.defaultAddress = defaultAddress;
    }
}
