package com.example.suryatraderscustomer.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SecondCategoryResult implements Serializable {

    @SerializedName("msg")
    @Expose
    private java.util.List<SecondCategoryMsg> secondCategoryMsg = null;

    public java.util.List<SecondCategoryMsg> getSecondCategoryMsg() {
        return secondCategoryMsg;
    }

    public void setSecondCategoryMsg(java.util.List<SecondCategoryMsg> secondCategoryMsg) {
        this.secondCategoryMsg = secondCategoryMsg;
    }
}
