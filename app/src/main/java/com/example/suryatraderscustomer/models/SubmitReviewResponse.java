package com.example.suryatraderscustomer.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubmitReviewResponse {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("result")
    @Expose
    private SubmitReviewResult result;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public SubmitReviewResult getResult() {
        return result;
    }

    public void setResult(SubmitReviewResult result) {
        this.result = result;
    }
}
