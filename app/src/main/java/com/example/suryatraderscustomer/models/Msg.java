package com.example.suryatraderscustomer.models;

import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.DiffUtil;

public class Msg implements Serializable {

    private static final String TAG="FirstCategory Model ";
    @SerializedName("product_code")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("cat_level")
    @Expose
    private Integer catLevel;
    @SerializedName("is_color_available")
    @Expose
    private Integer isColorAvailable;
    @SerializedName("units")
    @Expose
    private List<String> units;
    @SerializedName("colors")
    @Expose
    private List<Color> colorList;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("has_children")
    @Expose
    private Boolean hasChildren;
    @SerializedName("children")
    @Expose
    private java.util.List<SecondCategoryMsg> secondCategoryMsg;

    public Msg(String id, String name, Integer catLevel, Integer isColorAvailable, List<String> units, List<Color> colorList, String image, String description, Boolean hasChildren, List<SecondCategoryMsg> secondCategoryMsg) {
        this.id = id;
        this.name = name;
        this.catLevel = catLevel;
        this.isColorAvailable = isColorAvailable;
        this.units = units;
        this.colorList = colorList;
        this.image = image;
        this.description = description;
        this.hasChildren = hasChildren;
        this.secondCategoryMsg = secondCategoryMsg;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCatLevel() {
        return catLevel;
    }

    public void setCatLevel(Integer catLevel) {
        this.catLevel = catLevel;
    }

    public Integer getIsColorAvailable() {
        return isColorAvailable;
    }

    public void setIsColorAvailable(Integer isColorAvailable) {
        this.isColorAvailable = isColorAvailable;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public java.util.List<SecondCategoryMsg> getSecondCategoryMsg() {
        return secondCategoryMsg;
    }

    public void setSecondCategory(java.util.List<SecondCategoryMsg> secondCategoryMsg) {
        this.secondCategoryMsg = secondCategoryMsg;
    }

    public List<String> getUnits() {
        return units;
    }

    public void setUnits(List<String> units) {
        this.units = units;
    }

    public List<Color> getColorList() {
        return colorList;
    }

    public void setColorList(List<Color> colorList) {
        this.colorList = colorList;
    }

    public Boolean getHasChildren() {
        return hasChildren;
    }

    public void setHasChildren(Boolean hasChildren) {
        this.hasChildren = hasChildren;
    }

    public void setSecondCategoryMsg(List<SecondCategoryMsg> secondCategoryMsg) {
        this.secondCategoryMsg = secondCategoryMsg;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Msg msg = (Msg) o;
        return id.equals(msg.id) &&
                name.equals(msg.name) &&
                catLevel.equals(msg.catLevel) &&
                isColorAvailable.equals(msg.isColorAvailable) &&
                units.equals(msg.units) &&
                colorList.equals(msg.colorList) &&
                image.equals(msg.image) &&
                description.equals(msg.description) &&
                hasChildren.equals(msg.hasChildren) &&
                secondCategoryMsg.equals(msg.secondCategoryMsg);
    }



    public static DiffUtil.ItemCallback<Msg> itemCallback= new DiffUtil.ItemCallback<Msg>() {
        @Override
        public boolean areItemsTheSame(@androidx.annotation.NonNull Msg oldItem, @androidx.annotation.NonNull Msg newItem) {
            return oldItem.getId().equals(newItem.getId());
        }

        @Override
        public boolean areContentsTheSame(@androidx.annotation.NonNull Msg oldItem, @androidx.annotation.NonNull Msg newItem) {
            return oldItem.equals(newItem);
        }
    };

    @BindingAdapter("android:firstCategoryImage")
    public static void loadImage(ImageView imageview, String image)
    {
        Log.d( TAG,"Glide FirstCategoryImage");
        Glide.with(imageview).load("http://192.168.1.44:8080/All-Projects/MarkLift/Surya-Backend/surya/public"+image).fitCenter().into(imageview);
    }
}
