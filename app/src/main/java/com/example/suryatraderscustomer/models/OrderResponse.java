package com.example.suryatraderscustomer.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderResponse {

    @SerializedName("status")
    @Expose
    private Integer orderResponseStatus;
    @SerializedName("result")
    @Expose
    private OrderResult orderResponseResult;


    public Integer getOrderResponseStatus() {
        return orderResponseStatus;
    }

    public void setOrderResponseStatus(Integer orderResponseStatus) {
        this.orderResponseStatus = orderResponseStatus;
    }

    public OrderResult getOrderResponseResult() {
        return orderResponseResult;
    }

    public void setOrderResponseResult(OrderResult orderResponseResult) {
        this.orderResponseResult = orderResponseResult;
    }
}

