package com.example.suryatraderscustomer.models;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

public class CartItem {

    private Integer customer_id;
    private ThirdCategoryMsg thirdCategoryMsg;
    private SecondCategoryMsg secondCategoryMsg;

    public ThirdCategoryMsg getThirdCategoryMsg() {
        return thirdCategoryMsg;
    }

    public void setThirdCategoryMsg(ThirdCategoryMsg thirdCategoryMsg) {
        this.thirdCategoryMsg = thirdCategoryMsg;
    }

    public SecondCategoryMsg getSecondCategoryMsg() {
        return secondCategoryMsg;
    }

    public void setSecondCategoryMsg(SecondCategoryMsg secondCategoryMsg) {
        this.secondCategoryMsg = secondCategoryMsg;
    }

    public Integer getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(Integer customer_id) {
        this.customer_id = customer_id;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "customer_id=" + customer_id +
                ", thirdCategoryMsg=" + thirdCategoryMsg +
                ", secondCategoryMsg=" + secondCategoryMsg +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartItem cartItem = (CartItem) o;
        return customer_id.equals(cartItem.customer_id) &&
                thirdCategoryMsg.equals(cartItem.thirdCategoryMsg) &&
                secondCategoryMsg.equals(cartItem.secondCategoryMsg);
    }



    public static DiffUtil.ItemCallback<CartItem> itemCallback= new DiffUtil.ItemCallback<CartItem>() {
        @Override
        public boolean areItemsTheSame(@NonNull CartItem oldItem, @NonNull CartItem newItem) {
            return ((oldItem.getSecondCategoryMsg().equals(newItem.getSecondCategoryMsg()))
                    || (oldItem.getThirdCategoryMsg().equals(newItem.getThirdCategoryMsg())));
        }

        @Override
        public boolean areContentsTheSame(@NonNull CartItem oldItem, @NonNull CartItem newItem) {
            return oldItem.equals(newItem);
        }
    };
}
