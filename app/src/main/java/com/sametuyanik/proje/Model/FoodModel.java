package com.sametuyanik.proje.Model;

import com.google.gson.annotations.SerializedName;

public class FoodModel {

    @SerializedName("title")
    public String title;

    @SerializedName("image")
    public String image;

    @SerializedName("summary")
    public String summary;

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
