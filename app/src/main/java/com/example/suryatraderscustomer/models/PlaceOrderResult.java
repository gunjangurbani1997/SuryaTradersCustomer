package com.example.suryatraderscustomer.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PlaceOrderResult {

    @SerializedName("msg")
    @Expose
    private String placeOrderMsg;

    public String getPlaceOrderMsg() {
        return placeOrderMsg;
    }

    public void setPlaceOrderMsg(String placeOrderMsg) {
        this.placeOrderMsg = placeOrderMsg;
    }
}
