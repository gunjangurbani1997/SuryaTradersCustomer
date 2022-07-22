package com.example.suryatraderscustomer.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import androidx.recyclerview.widget.DiffUtil;

public class Orders {

    @SerializedName("order_id")
    @Expose
    private Integer orderId;
    @SerializedName("customer_id")
    @Expose
    private Integer customerId;
    @SerializedName("display_status")
    @Expose
    private String status;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("no_of_bales_packed")
    @Expose
    private String no_of_bales_packed;
    @SerializedName("sub_orders")
    @Expose
    private List<SubOrders> subOrderList;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getNo_of_bales_packed() {
        return no_of_bales_packed;
    }

    public void setNo_of_bales_packed(String no_of_bales_packed) {
        this.no_of_bales_packed = no_of_bales_packed;
    }

    public List<SubOrders> getSubOrderList() {
        return subOrderList;
    }

    public void setSubOrderList(List<SubOrders> subOrderList) {
        this.subOrderList = subOrderList;
    }


    public Orders(Integer orderId, Integer customerId, String status, String createdAt, String updatedAt,String no_of_bales_packed, List<SubOrders> subOrderList) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.no_of_bales_packed=no_of_bales_packed;
        this.subOrderList = subOrderList;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Orders orders = (Orders) o;
        return orderId.equals(orders.orderId) &&
                customerId.equals(orders.customerId) &&
                status.equals(orders.status) &&
                createdAt.equals(orders.createdAt) &&
                updatedAt.equals(orders.updatedAt) &&
                no_of_bales_packed.equals(orders.no_of_bales_packed)&&
                subOrderList.equals(orders.subOrderList);
    }

    public static DiffUtil.ItemCallback<Orders> itemCallback= new DiffUtil.ItemCallback<Orders>() {
        @Override
        public boolean areItemsTheSame(@androidx.annotation.NonNull Orders oldItem, @androidx.annotation.NonNull Orders newItem) {
            return oldItem.getOrderId().equals(newItem.getOrderId());
        }

        @Override
        public boolean areContentsTheSame(@androidx.annotation.NonNull Orders oldItem, @androidx.annotation.NonNull Orders newItem) {
            return oldItem.equals(newItem);
        }
    };
}
