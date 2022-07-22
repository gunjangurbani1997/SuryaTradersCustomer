package com.example.suryatraderscustomer.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("msg")
    @Expose
    private java.util.List<Msg> msg = null;

    public java.util.List<Msg> getMsg() {
        return msg;
    }

    public void setMsg(java.util.List<Msg> msg) {
        this.msg = msg;
    }
}
