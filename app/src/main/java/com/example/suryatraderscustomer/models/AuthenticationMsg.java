package com.example.suryatraderscustomer.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AuthenticationMsg {

    @Expose
    @SerializedName("msg")
    String authenticationMsg;

    @Expose
    @SerializedName("customer_id")
    String customer_id;

    public String getAuthenticationMsg() {
        return authenticationMsg;
    }

    public void setAuthenticationMsg(String authenticationMsg) {
        this.authenticationMsg = authenticationMsg;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }
}
