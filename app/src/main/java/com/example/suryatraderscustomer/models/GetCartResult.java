package com.example.suryatraderscustomer.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class GetCartResult implements Serializable {

    @SerializedName("msg")
    @Expose
    private List<GetCartMsg> cartList;

    @SerializedName("error_msg")
    @Expose
    private String errorMsg;

    @SerializedName("cartTotal")
    @Expose
    private Double cartTotal;

    public List<GetCartMsg> getCartList() {
        return cartList;
    }

    public void setCartList(List<GetCartMsg> cartList) {
        this.cartList = cartList;
    }

    public Double getCartTotal() {
        return cartTotal;
    }

    public void setCartTotal(Double cartTotal) {
        this.cartTotal = cartTotal;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
