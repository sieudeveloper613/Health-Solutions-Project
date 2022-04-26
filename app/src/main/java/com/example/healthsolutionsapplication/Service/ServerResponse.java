package com.example.healthsolutionsapplication.Service;

import com.example.healthsolutionsapplication.Model.Address;
import com.example.healthsolutionsapplication.Model.Cart;
import com.example.healthsolutionsapplication.Model.Customer;
import com.example.healthsolutionsapplication.Model.Feedback;
import com.example.healthsolutionsapplication.Model.Knowledge;
import com.example.healthsolutionsapplication.Model.Notification;
import com.example.healthsolutionsapplication.Model.Product;
import com.example.healthsolutionsapplication.Model.Type;
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

    @SerializedName("cartList")
    @Expose
    private List<Cart> cartList = null;

    @SerializedName("notification")
    @Expose
    private Notification notification;

    @SerializedName("notificationList")
    @Expose
    private List<Notification> notificationList = null;

    @SerializedName("feedback")
    @Expose
    private Feedback feedback;

    @SerializedName("feedbackList")
    @Expose
    private List<Feedback> feedbackList = null;

    @SerializedName("typeList")
    @Expose
    private List<Type> typeList = null;

    @SerializedName("knowledgeList")
    @Expose
    private List<Knowledge> knowledgeList = null;

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

    public Feedback getFeedback() {
        return feedback;
    }

    public void setFeedback(Feedback feedback) {
        this.feedback = feedback;
    }

    public List<Feedback> getFeedbackList() {
        return feedbackList;
    }

    public void setFeedbackList(List<Feedback> feedbackList) {
        this.feedbackList = feedbackList;
    }

    public List<Knowledge> getKnowledgeList() {
        return knowledgeList;
    }

    public void setKnowledgeList(List<Knowledge> knowledgeList) {
        this.knowledgeList = knowledgeList;
    }

    public Notification getNotification() {
        return notification;
    }

    public void setNotification(Notification notification) {
        this.notification = notification;
    }

    public List<Notification> getNotificationList() {
        return notificationList;
    }

    public void setNotificationList(List<Notification> notificationList) {
        this.notificationList = notificationList;
    }

    public List<Type> getTypeList() {
        return typeList;
    }

    public void setTypeList(List<Type> typeList) {
        this.typeList = typeList;
    }

    public List<Cart> getCartList() {
        return cartList;
    }

    public void setCartList(List<Cart> cartList) {
        this.cartList = cartList;
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
