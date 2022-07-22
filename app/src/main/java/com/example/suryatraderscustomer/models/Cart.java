package com.example.suryatraderscustomer.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Cart implements Serializable {

    @Expose
    @SerializedName("customer_id")
    private Integer customer_id;

    @Expose
    @SerializedName("cat_level")
    private Integer cat_level;

    @Expose
    @SerializedName("product_code")
    private Integer product_code;

    @Expose
    @SerializedName("req_data")
    private List<ReqData> reqDataList;

    public Integer getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(Integer customer_id) {
        this.customer_id = customer_id;
    }

    public Integer getCat_level() {
        return cat_level;
    }

    public void setCat_level(Integer cat_level) {
        this.cat_level = cat_level;
    }

    public Integer getProduct_code() {
        return product_code;
    }

    public void setProduct_code(Integer product_code) {
        this.product_code = product_code;
    }

    public List<ReqData> getReqDataList() {
        return reqDataList;
    }

    public void setReqDataList(List<ReqData> reqDataList) {
        this.reqDataList = reqDataList;
    }
}


