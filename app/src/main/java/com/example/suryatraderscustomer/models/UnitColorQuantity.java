package com.example.suryatraderscustomer.models;



import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.example.suryatraderscustomer.models.Color;

import java.util.List;
import java.util.Objects;

import androidx.recyclerview.widget.DiffUtil;

public class UnitColorQuantity {


    @SerializedName("colors")
    @Expose
    private Color color;
    @SerializedName("units")
    @Expose
    private String units;
    @SerializedName("quantity")
    @Expose
    private String quantity;

    private List<ReqData> reqDataList;

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public List<ReqData> getReqDataList() {
        return reqDataList;
    }

    public void setReqDataList(List<ReqData> reqDataList) {
        this.reqDataList = reqDataList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UnitColorQuantity that = (UnitColorQuantity) o;
        return color.equals(that.color) &&
                units.equals(that.units) &&
                quantity.equals(that.quantity) &&
                reqDataList.equals(that.reqDataList);
    }


    public static DiffUtil.ItemCallback<UnitColorQuantity> itemCallback= new DiffUtil.ItemCallback<UnitColorQuantity>() {


        @Override
        public boolean areItemsTheSame(@androidx.annotation.NonNull UnitColorQuantity oldItem, @androidx.annotation.NonNull UnitColorQuantity newItem) {
            return false;
        }

        @Override
        public boolean areContentsTheSame(@androidx.annotation.NonNull UnitColorQuantity oldItem, @androidx.annotation.NonNull UnitColorQuantity newItem) {
            return oldItem.equals(newItem);
        }
    };
}
