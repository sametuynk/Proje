package com.sametuyanik.proje.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Receipts<T> {
    @SerializedName("recipes")
    public List<FoodModel> receipts;

    public List<FoodModel> getReceipts() {
        return receipts;
    }

    public void setReceipts(List<FoodModel> receipts) {
        this.receipts = receipts;
    }
}
