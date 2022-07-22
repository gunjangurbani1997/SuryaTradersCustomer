package com.example.suryatraderscustomer.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ThirdCategoryResult {

    @SerializedName("msg")
    @Expose
    private java.util.List<ThirdCategoryMsg> thirdCategoryMsg = null;

    public java.util.List<ThirdCategoryMsg> getThirdCategoryMsg() {
        return thirdCategoryMsg;
    }

    public void setThirdCategoryMsg(java.util.List<ThirdCategoryMsg> thirdCategoryMsg) {
        this.thirdCategoryMsg = thirdCategoryMsg;
    }
}
