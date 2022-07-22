package com.example.suryatraderscustomer.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.suryatraderscustomer.databinding.ThirdCategoryRowBinding;
import com.example.suryatraderscustomer.models.ThirdCategoryMsg;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class ThirdCategoryListAdapter extends ListAdapter<ThirdCategoryMsg,ThirdCategoryListAdapter.ThirdCategoryViewHolder> {

    private static final String TAG="ThirdCategoryListAdapter";
    ThirdCategoryInterface thirdCategoryInterface;


    public ThirdCategoryListAdapter(ThirdCategoryInterface thirdCategoryInterface) {

        super(ThirdCategoryMsg.itemCallback);
        this.thirdCategoryInterface=thirdCategoryInterface;
    }

    @NonNull
    @Override
    public ThirdCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        ThirdCategoryRowBinding thirdCategoryRowBinding=ThirdCategoryRowBinding.inflate(layoutInflater, parent,false);
        thirdCategoryRowBinding.setThirdCategoryInterface(thirdCategoryInterface);
        return new ThirdCategoryViewHolder(thirdCategoryRowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ThirdCategoryViewHolder holder, int position) {
        ThirdCategoryMsg thirdCategoryMsg=getItem(position);
        holder.thirdCategoryRowBinding.setThirdCategoryMsg(thirdCategoryMsg);
    }


    public class ThirdCategoryViewHolder extends RecyclerView.ViewHolder {

        ThirdCategoryRowBinding thirdCategoryRowBinding;
        public ThirdCategoryViewHolder(ThirdCategoryRowBinding binding)
        {
            super(binding.getRoot());
            this.thirdCategoryRowBinding=binding;



        }

    }

    public interface ThirdCategoryInterface
    {
        public void onThirdCategoryItemClick(ThirdCategoryMsg thirdCategoryMsg);
    }



}
