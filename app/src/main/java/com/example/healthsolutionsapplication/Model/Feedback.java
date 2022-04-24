package com.example.healthsolutionsapplication.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Feedback {
    @SerializedName("idFeedback")
    @Expose
    private int idFeedback;
    @SerializedName("idCustomer")
    @Expose
    private String idCustomer;
    @SerializedName("nameCustomer")
    @Expose
    private String nameCustomer;
    @SerializedName("titleFeedback")
    @Expose
    private String titleFeedback;
    @SerializedName("contentFeedback")
    @Expose
    private String contentFeedback;

    public int getIdFeedback() {
        return idFeedback;
    }

    public void setIdFeedback(int idFeedback) {
        this.idFeedback = idFeedback;
    }

    public String getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(String idCustomer) {
        this.idCustomer = idCustomer;
    }

    public String getNameCustomer() {
        return nameCustomer;
    }

    public void setNameCustomer(String nameCustomer) {
        this.nameCustomer = nameCustomer;
    }

    public String getTitleFeedback() {
        return titleFeedback;
    }

    public void setTitleFeedback(String titleFeedback) {
        this.titleFeedback = titleFeedback;
    }

    public String getContentFeedback() {
        return contentFeedback;
    }

    public void setContentFeedback(String contentFeedback) {
        this.contentFeedback = contentFeedback;
    }
}
