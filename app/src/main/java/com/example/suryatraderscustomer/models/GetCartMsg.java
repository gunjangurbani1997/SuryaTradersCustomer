package com.example.suryatraderscustomer.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import androidx.recyclerview.widget.DiffUtil;

public class GetCartMsg implements Serializable {

    @Expose
    @SerializedName("customer_id")
    private Integer customer_id;

    @Expose
    @SerializedName("cart_id")
    private Integer cartId;

    @Expose
    @SerializedName("cat_level")
    private Integer cat_level;

    @Expose
    @SerializedName("unit")
    private String units;
    @Expose
    @SerializedName("quantity")
    private Integer quantity;
    @Expose
    @SerializedName("product_code")
    private String product_code;

    @Expose
    @SerializedName("product_name")
    private String productName;

    @Expose
    @SerializedName("price")
    private Double price;

    @Expose
    @SerializedName("product_price_with_gst")
    private Double productPriceWithGst;

    @Expose
    @SerializedName("color_name")
    private String colorName;

    @Expose
    @SerializedName("color_code")
    private String colorCode;

    @Expose
    @SerializedName("image")
    private String image;

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

    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    public Integer getCat_level() {
        return cat_level;
    }

    public void setCat_level(Integer cat_level) {
        this.cat_level = cat_level;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getProduct_code() {
        return product_code;
    }

    public void setProduct_code(String product_code) {
        this.product_code = product_code;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getProductPriceWithGst() {
        return productPriceWithGst;
    }

    public void setProductPriceWithGst(Double productPriceWithGst) {
        this.productPriceWithGst = productPriceWithGst;
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    public String getColorCode() {
        return colorCode;
    }

    public void setColorCode(String colorCode) {
        this.colorCode = colorCode;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GetCartMsg cartItem = (GetCartMsg) o;
        return  cartId.equals(cartItem.cartId) &&
                cat_level.equals(cartItem.cat_level) &&
                units.equals(cartItem.units) &&
                quantity.equals(cartItem.quantity) &&
                product_code.equals(cartItem.product_code) &&
                price.equals(cartItem.price) &&
                productPriceWithGst.equals(cartItem.productPriceWithGst) &&
                colorName.equals(cartItem.colorName) &&
                colorCode.equals(cartItem.colorCode) &&
                image.equals(cartItem.image) &&
                productName.equals(cartItem.productName);
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "customer_id=" + customer_id +
                ", cartId=" + cartId +
                ", cat_level=" + cat_level +
                ", units='" + units + '\'' +
                ", quantity=" + quantity +
                ", product_code='" + product_code + '\'' +
                ", price=" + price +
                ", productPriceWithGst=" + productPriceWithGst +
                ", colorName='" + colorName + '\'' +
                ", colorCode='" + colorCode + '\'' +
                ", image='" + image + '\'' +
                ", productName'" + productName + '\'' +
                ", thirdCategoryMsg=" + thirdCategoryMsg +
                ", secondCategoryMsg=" + secondCategoryMsg +
                '}';
    }

    public static DiffUtil.ItemCallback<GetCartMsg> itemCallback= new DiffUtil.ItemCallback<GetCartMsg>() {
        @Override
        public boolean areItemsTheSame(@androidx.annotation.NonNull GetCartMsg oldItem, @androidx.annotation.NonNull GetCartMsg newItem) {
            return oldItem.getCartId().equals(newItem.getCartId());
        }

        @Override
        public boolean areContentsTheSame(@androidx.annotation.NonNull GetCartMsg oldItem, @androidx.annotation.NonNull GetCartMsg newItem) {
            return oldItem.equals(newItem);
        }
    };

    public GetCartMsg() {
    }
}
