package com.example.suryatraderscustomer.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.suryatraderscustomer.R;
import com.example.suryatraderscustomer.databinding.ProductDetailImageSliderBinding;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

public class ImageSliderAdapter extends RecyclerView.Adapter<ImageSliderAdapter.ImageSliderViewHolder>{

    private static String sliderImages[];
    private LayoutInflater layoutInflater;

    public ImageSliderAdapter(String[] sliderImages)
    {
        this.sliderImages=sliderImages;
    }

    @NonNull
    @Override
    public ImageSliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (layoutInflater==null)
        {
            layoutInflater=LayoutInflater.from(parent.getContext());
        }
        ProductDetailImageSliderBinding productDetailImageSliderBinding= DataBindingUtil.inflate(layoutInflater, R.layout.product_detail_image_slider,
                parent,false);

        return new ImageSliderViewHolder(productDetailImageSliderBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageSliderViewHolder holder, int position) {
        /*holder.bindProductDetailSliderImage(sliderImages[position]);*/

    }

    @Override
    public int getItemCount() {
        return sliderImages.length;
    }


    public static class ImageSliderViewHolder extends RecyclerView.ViewHolder
    {
        private ProductDetailImageSliderBinding productDetailImageSliderBinding;

        private ImageSliderViewHolder(ProductDetailImageSliderBinding productDetailImageSliderBinding)
        {
            super(productDetailImageSliderBinding.getRoot());
            this.productDetailImageSliderBinding=productDetailImageSliderBinding;
        }

       /* public void bindProductDetailSliderImage(String imageUrl)
        {
            productDetailImageSliderBinding.setList();
        }*/



    }


}
