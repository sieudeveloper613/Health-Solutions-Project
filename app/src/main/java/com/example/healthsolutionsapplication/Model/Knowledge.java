package com.example.healthsolutionsapplication.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Knowledge {
    @SerializedName("idKnowledge")
    @Expose
    private String idKnowledge;
    @SerializedName("titleKnowledge")
    @Expose
    private String titleKnowledge;
    @SerializedName("contentKnowledge")
    @Expose
    private String contentKnowledge;
    @SerializedName("imageKnowledge")
    @Expose
    private String imageKnowledge;

    public String getIdKnowledge() {
        return idKnowledge;
    }

    public void setIdKnowledge(String idKnowledge) {
        this.idKnowledge = idKnowledge;
    }

    public String getTitleKnowledge() {
        return titleKnowledge;
    }

    public void setTitleKnowledge(String titleKnowledge) {
        this.titleKnowledge = titleKnowledge;
    }

    public String getContentKnowledge() {
        return contentKnowledge;
    }

    public void setContentKnowledge(String contentKnowledge) {
        this.contentKnowledge = contentKnowledge;
    }

    public String getImageKnowledge() {
        return imageKnowledge;
    }

    public void setImageKnowledge(String imageKnowledge) {
        this.imageKnowledge = imageKnowledge;
    }
}
