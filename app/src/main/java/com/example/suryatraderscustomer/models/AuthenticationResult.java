package com.example.suryatraderscustomer.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AuthenticationResult implements Serializable {

    @SerializedName("msg")
    @Expose
    private String authenticationMsg ;
    @SerializedName("customer_id")
    @Expose
    private String customerId ;
    @SerializedName("token")
    @Expose
    private Token token = null;

    public String getAuthenticationMsg() {
        return authenticationMsg;
    }

    public void setAuthenticationMsg(String authenticationMsg) {
        this.authenticationMsg = authenticationMsg;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }
}
