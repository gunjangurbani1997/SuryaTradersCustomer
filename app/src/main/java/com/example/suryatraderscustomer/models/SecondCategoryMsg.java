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

import androidx.annotation.Nullable;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.room.Ignore;

public class SecondCategoryMsg implements Serializable , Parcelable {

    private static final String TAG="SecondCategory Model ";

    @SerializedName("product_code")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("cat_1_product_code")
    @Expose
    private String cat_1_id;
    @SerializedName("is_color_available")
    @Expose
    private Integer isColorAvailable;
    @SerializedName("unit")
    @Expose
   /* private java.util.List<String> units;*/
    private String units;
    @SerializedName("colors")
    @Expose
    private java.util.List<Color> colors;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("has_children")
    @Expose
    private Boolean hasChildren;
    @SerializedName("mtr_price")
    @Nullable
    private String price;
    @SerializedName("children")
    @Expose
    private java.util.List<ThirdCategoryMsg> thirdCategoryMsg;
    @Expose
    private java.util.List<UnitColorQuantity> unitColorQuantityList;
   /* @SerializedName("upcoming")
    private Integer upcoming;*/
    @SerializedName("out_of_stock")
    private Integer out_of_stock;

    public SecondCategoryMsg() {
    }

    protected SecondCategoryMsg(android.os.Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readString();
        }
        name = in.readString();
        if (in.readByte() == 0) {
            cat_1_id = null;
        } else {
            cat_1_id = in.readString();
        }
        if (in.readByte() == 0) {
            isColorAvailable = null;
        } else {
            isColorAvailable = in.readInt();
        }
         units = in.readString();
        image = in.readString();
        description = in.readString();
    }

    public static final Creator<SecondCategoryMsg> CREATOR = new Creator<SecondCategoryMsg>() {
        @Override
        public SecondCategoryMsg createFromParcel(android.os.Parcel in) {
            return new SecondCategoryMsg(in);
        }

        @Override
        public SecondCategoryMsg[] newArray(int size) {
            return new SecondCategoryMsg[size];
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

    public String getCat_1_id() {
        return cat_1_id;
    }

    public void setCat_1_id(String cat_1_id) {
        this.cat_1_id = cat_1_id;
    }

   /* public java.util.List<String> getUnits() {
        return units;
    }

    public void setUnits(java.util.List<String> units) {
        this.units = units;
    }
*/

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

    public java.util.List<ThirdCategoryMsg> getThirdCategoryMsg() {
        return thirdCategoryMsg;
    }

    public void setThirdCategoryMsg(java.util.List<ThirdCategoryMsg> thirdCategoryMsg) {
        this.thirdCategoryMsg = thirdCategoryMsg;
    }

    public List<UnitColorQuantity> getUnitColorQuantityList() {
        return unitColorQuantityList;
    }

    public void setUnitColorQuantityList(List<UnitColorQuantity> unitColorQuantityList) {
        this.unitColorQuantityList = unitColorQuantityList;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SecondCategoryMsg that = (SecondCategoryMsg) o;
        return id.equals(that.id) &&
                name.equals(that.name) &&
                cat_1_id.equals(that.cat_1_id) &&
                isColorAvailable.equals(that.isColorAvailable) &&
                units.equals(that.units) &&
                colors.equals(that.colors) &&
                image.equals(that.image) &&
                description.equals(that.description) &&
                hasChildren.equals(that.hasChildren) &&
                thirdCategoryMsg.equals(that.thirdCategoryMsg)&&
                price.equals(price)&&
                unitColorQuantityList.equals(unitColorQuantityList);
    }



    public static DiffUtil.ItemCallback<SecondCategoryMsg> itemCallback= new DiffUtil.ItemCallback<SecondCategoryMsg>() {


        @Override
        public boolean areItemsTheSame(@androidx.annotation.NonNull SecondCategoryMsg oldItem, @androidx.annotation.NonNull SecondCategoryMsg newItem) {
            return false;
        }

        @Override
        public boolean areContentsTheSame(@androidx.annotation.NonNull SecondCategoryMsg oldItem, @androidx.annotation.NonNull SecondCategoryMsg newItem) {
            return oldItem.equals(newItem);
        }
    };

    @BindingAdapter("android:secondCategoryImage")
    public static void loadImage(ImageView imageview, String image)
    {
        Log.d( TAG,"Glide SecondCategoryImage");
        Glide.with(imageview).load("http://192.168.1.44:8080/All-Projects/MarkLift/Surya-Backend/surya/public"+image).fitCenter().into(imageview);
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
        if (cat_1_id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeString(cat_1_id);
        }
        if (isColorAvailable == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(isColorAvailable);
        }
        dest.writeString(units);
        dest.writeString(image);
        dest.writeString(description);
    }


    @Override
    public String toString() {
        return "SecondCategoryMsg{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cat_1_id=" + cat_1_id +
                ", isColorAvailable=" + isColorAvailable +
                ", units=" + units +
                ", colors=" + colors +
                ", image='" + image + '\'' +
                ", description='" + description + '\'' +
                ", hasChildren='" + hasChildren + '\'' +
                ", thirdCategoryMsg=" + thirdCategoryMsg +
                ", price=" + price +
                ", unitColorQuantityList=" + unitColorQuantityList +
                '}';
    }

    public SecondCategoryMsg(String id, String name, String cat_1_id, Integer isColorAvailable, String units, List<Color> colors, String image, String description, Boolean hasChildren, List<ThirdCategoryMsg> thirdCategoryMsg,List<UnitColorQuantity> unitColorQuantityList,String price) {
        this.id = id;
        this.name = name;
        this.cat_1_id = cat_1_id;
        this.isColorAvailable = isColorAvailable;
        this.units = units;
        this.colors = colors;
        this.image = image;
        this.description = description;
        this.hasChildren = hasChildren;
        this.thirdCategoryMsg = thirdCategoryMsg;
        this.price=price;
        this.unitColorQuantityList=unitColorQuantityList;
    }
}
