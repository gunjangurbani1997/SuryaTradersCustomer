package com.example.suryatraderscustomer.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetCartResponse {
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("result")
    @Expose
    private GetCartResult getCartResult;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public GetCartResult getGetCartResult() {
        return getCartResult;
    }

    public void setGetCartResult(GetCartResult getCartResult) {
        this.getCartResult = getCartResult;
    }
}
