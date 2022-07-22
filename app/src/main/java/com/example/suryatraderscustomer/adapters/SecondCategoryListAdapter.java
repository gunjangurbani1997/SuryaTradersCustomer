package com.example.suryatraderscustomer.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.suryatraderscustomer.databinding.SecondCategoryRowBinding;
import com.example.suryatraderscustomer.models.SecondCategoryMsg;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class SecondCategoryListAdapter extends ListAdapter<SecondCategoryMsg,SecondCategoryListAdapter.SecondCategoryViewHolder> {

    private static final String TAG="SecondCategoryListAdapter";
    SecondCategoryInterface secondCategoryInterface;
    public SecondCategoryListAdapter(SecondCategoryInterface secondCategoryInterface)
    {
        super(SecondCategoryMsg.itemCallback);
        this.secondCategoryInterface=secondCategoryInterface;

    }

    @NonNull
    @Override
    public SecondCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        SecondCategoryRowBinding secondCategoryRowBinding=SecondCategoryRowBinding.inflate(layoutInflater, parent,false);
        secondCategoryRowBinding.setSecondCategoryInterface(secondCategoryInterface);
        return new SecondCategoryViewHolder(secondCategoryRowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull SecondCategoryViewHolder holder, int position) {

        SecondCategoryMsg secondCategoryMsg=getItem(position);
        holder.secondCategoryRowBinding.setSecondCategoryMsg(secondCategoryMsg);
    }
    public class SecondCategoryViewHolder extends RecyclerView.ViewHolder {

        SecondCategoryRowBinding secondCategoryRowBinding;
        public SecondCategoryViewHolder(SecondCategoryRowBinding binding)
        {
            super(binding.getRoot());
            this.secondCategoryRowBinding=binding;

        }

    }

    public interface SecondCategoryInterface
    {

        public void onSecondCategoryItemClick(SecondCategoryMsg secondCategoryMsg);


    }




}

