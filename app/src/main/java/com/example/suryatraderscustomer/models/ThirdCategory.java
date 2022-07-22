package com.example.suryatraderscustomer.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ThirdCategory {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("result")
    @Expose
    private ThirdCategoryResult result;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public ThirdCategoryResult getResult() {
        return result;
    }

    public void setResult(ThirdCategoryResult result) {
        this.result = result;
    }
}
