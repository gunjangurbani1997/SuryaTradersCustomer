package com.example.suryatraderscustomer.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CartResult {

    @SerializedName("msg")
    @Expose
    private String cartMsg;

    public String getCartMsg() {
        return cartMsg;
    }

    public void setCartMsg(String cartMsg) {
        this.cartMsg = cartMsg;
    }
}
