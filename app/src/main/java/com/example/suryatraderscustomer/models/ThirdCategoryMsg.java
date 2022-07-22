package com.example.suryatraderscustomer.models;


import android.os.Parcelable;
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

public class ThirdCategoryMsg implements Serializable , Parcelable {

    private static final String TAG="ThirdCategory Model ";


    @SerializedName("product_code")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("cat_2_product_code")
    @Expose
    private String cat_2_id;
    @SerializedName("is_color_available")
    @Expose
    private Integer isColorAvailable;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("unit")
    @Expose
   /* private java.util.List<String> units;*/
    private String units;
    @SerializedName("colors")
    @Expose
    private java.util.List<Color> colors;
    @Expose
    private java.util.List<UnitColorQuantity> unitColorQuantityList;

    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("cat_level")
    @Expose
    private String categoryLevel;
    @SerializedName("has_children")
    @Expose
    private Boolean hasChildren;
    @SerializedName("children")
    @Expose
    private java.util.List<Child> children;
    @SerializedName("mtr_price")
    private String price;
    /*@SerializedName("upcoming")
    private Integer upcoming;
   */ @SerializedName("out_of_stock")
    private Integer out_of_stock;

    public ThirdCategoryMsg() {
    }

    protected ThirdCategoryMsg(android.os.Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readString();
        }
        name = in.readString();
        if (in.readByte() == 0) {
            cat_2_id = null;
        } else {
            cat_2_id = in.readString();
        }
        if (in.readByte() == 0) {
            isColorAvailable = null;
        } else {
            isColorAvailable = in.readInt();
        }
        image = in.readString();
        units = in.readString();
        description = in.readString();
    }

    public static final Creator<ThirdCategoryMsg> CREATOR = new Creator<ThirdCategoryMsg>() {
        @Override
        public ThirdCategoryMsg createFromParcel(android.os.Parcel in) {
            return new ThirdCategoryMsg(in);
        }

        @Override
        public ThirdCategoryMsg[] newArray(int size) {
            return new ThirdCategoryMsg[size];
        }
    };

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

    public String getCat_2_id() {
        return cat_2_id;
    }

    public void setCat_2_id(String cat_2_id) {
        this.cat_2_id = cat_2_id;
    }

    public Integer getIsColorAvailable() {
        return isColorAvailable;
    }

    public void setIsColorAvailable(Integer isColorAvailable) {
        this.isColorAvailable = isColorAvailable;
    }

   /* public java.util.List<String> getUnits() {
        return units;
    }

    public void setUnits(java.util.List<String> units) {
        this.units = units;
    }*/

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public java.util.List<Color> getColors() {
        return colors;
    }

    public void setColors(java.util.List<Color> colors) {
        this.colors = colors;
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

    public java.util.List<UnitColorQuantity> getUnitColorQuantityList() {
        return unitColorQuantityList;
    }

    public void setUnitColorQuantityList(java.util.List<UnitColorQuantity> unitColorQuantityList) {
        this.unitColorQuantityList = unitColorQuantityList;
    }

    public java.util.List<Child> getChildren() {
        return children;
    }

    public void setChildren(java.util.List<Child> children) {
        this.children = children;
    }

    public String getCategoryLevel() {
        return categoryLevel;
    }

    public void setCategoryLevel(String categoryLevel) {
        this.categoryLevel = categoryLevel;
    }

    public Boolean getHasChildren() {
        return hasChildren;
    }

    public void setHasChildren(Boolean hasChildren) {
        this.hasChildren = hasChildren;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

   /* public Integer getUpcoming() {
        return upcoming;
    }

    public void setUpcoming(Integer upcoming) {
        this.upcoming = upcoming;
    }
*/
    public Integer getOut_of_stock() {
        return out_of_stock;
    }

    public void setOut_of_stock(Integer out_of_stock) {
        this.out_of_stock = out_of_stock;
    }

    public ThirdCategoryMsg(String id, String name, String cat_2_id, Integer isColorAvailable, String image, String units, List<Color> colors, List<UnitColorQuantity> unitColorQuantityList, String description, Boolean hasChildren, List<Child> children, String price) {
        this.id = id;
        this.name = name;
        this.cat_2_id = cat_2_id;
        this.isColorAvailable = isColorAvailable;
        this.image = image;
        this.units = units;
        this.colors = colors;
        this.unitColorQuantityList = unitColorQuantityList;
        this.description = description;
        this.hasChildren = hasChildren;
        this.children = children;
        this.price=price;
        this.categoryLevel=getCategoryLevel();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ThirdCategoryMsg that = (ThirdCategoryMsg) o;
        return id.equals(that.id) &&
                name.equals(that.name) &&
                cat_2_id.equals(that.cat_2_id) &&
                isColorAvailable.equals(that.isColorAvailable) &&
                image.equals(that.image) &&
                units.equals(that.units) &&
                colors.equals(that.colors) &&
                unitColorQuantityList.equals(that.unitColorQuantityList) &&
                description.equals(that.description) &&
                hasChildren.equals(that.hasChildren) &&
                categoryLevel.equals(that.categoryLevel)&&
                price.equals(price)&&
                children.equals(that.children);

    }

    public static DiffUtil.ItemCallback<ThirdCategoryMsg> itemCallback= new DiffUtil.ItemCallback<ThirdCategoryMsg>() {


        @Override
        public boolean areItemsTheSame(@androidx.annotation.NonNull ThirdCategoryMsg oldItem, @androidx.annotation.NonNull ThirdCategoryMsg newItem) {
            return false;
        }

        @Override
        public boolean areContentsTheSame(@androidx.annotation.NonNull ThirdCategoryMsg oldItem, @androidx.annotation.NonNull ThirdCategoryMsg newItem) {
            return oldItem.equals(newItem);
        }
    };

    @BindingAdapter("android:thirdCategoryImage")
    public static void loadImage(ImageView imageview, String image)
    {
        Log.d( TAG,"Glide ThirdCategoryImage "+image);
        Glide.with(imageview).load("http://192.168.1.44:8080/All-Projects/MarkLift/Surya-Backend/surya/public"+image).fitCenter().into(imageview);
    }

    @Override
    public String toString() {
        return "ThirdCategoryMsg{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cat_2_id=" + cat_2_id +
                ", isColorAvailable=" + isColorAvailable +
                ", image='" + image + '\'' +
                ", units=" + units +
                ", colors=" + colors +
                ", unitColorQuantityList=" + unitColorQuantityList +
                ", description='" + description + '\'' +
                ", categoryLevel'"+categoryLevel+ '\''+
                ", price'"+price+ '\''+
                ", children=" + children +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeString(id);
        }
        dest.writeString(name);
        if (cat_2_id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeString(cat_2_id);
        }
        if (isColorAvailable == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(isColorAvailable);
        }
        dest.writeString(image);
        dest.writeString(units);
        dest.writeString(description);
    }


}
