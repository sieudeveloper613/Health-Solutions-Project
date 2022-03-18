package com.example.healthsolutionsapplication.Model;

public class DeliveryAddress {
    private int idAddress;
    private String nameAddress;
    private String phoneNumberAddress;
    private String locationAddress;
    private boolean isDefaultAddress;

    public DeliveryAddress(int idAddress, String nameAddress, String phoneNumberAddress, String locationAddress, boolean isDefaultAddress) {
        this.idAddress = idAddress;
        this.nameAddress = nameAddress;
        this.phoneNumberAddress = phoneNumberAddress;
        this.locationAddress = locationAddress;
        this.isDefaultAddress = isDefaultAddress;
    }

    public DeliveryAddress(){}

    public int getIdAddress() {
        return idAddress;
    }

    public void setIdAddress(int idAddress) {
        this.idAddress = idAddress;
    }

    public String getNameAddress() {
        return nameAddress;
    }

    public void setNameAddress(String nameAddress) {
        this.nameAddress = nameAddress;
    }

    public String getPhoneNumberAddress() {
        return phoneNumberAddress;
    }

    public void setPhoneNumberAddress(String phoneNumberAddress) {
        this.phoneNumberAddress = phoneNumberAddress;
    }

    public String getLocationAddress() {
        return locationAddress;
    }

    public void setLocationAddress(String locationAddress) {
        this.locationAddress = locationAddress;
    }

    public boolean isDefaultAddress() {
        return isDefaultAddress;
    }

    public void setDefaultAddress(boolean defaultAddress) {
        isDefaultAddress = defaultAddress;
    }
}
