package com.example.suryatraderscustomer.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RemoveCartItemResult {

    @SerializedName("msg")
    @Expose
    private String removeItemFromCartMsg;

    public String getRemoveItemFromCartMsg() {
        return removeItemFromCartMsg;
    }

    public void setRemoveItemFromCartMsg(String removeItemFromCartMsg) {
        this.removeItemFromCartMsg = removeItemFromCartMsg;
    }
}
