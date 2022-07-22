package com.example.suryatraderscustomer.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.suryatraderscustomer.databinding.FirstCategoryRowBinding;
import com.example.suryatraderscustomer.models.Msg;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class FirstCategoryListAdapter extends ListAdapter<Msg, FirstCategoryListAdapter.FirstCategoryViewHolder> {

    private static final String TAG="FirstCategoryListAdapter";
    FirstCategoryInterface firstCategoryInterface;
    public FirstCategoryListAdapter(FirstCategoryInterface firstCategoryInterface)
    {
        super(Msg.itemCallback);
        this.firstCategoryInterface=firstCategoryInterface;
    }

    @NonNull
    @Override
    public FirstCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        FirstCategoryRowBinding firstCategoryRowBinding=FirstCategoryRowBinding.inflate(layoutInflater, parent,false);
        firstCategoryRowBinding.setFirstCategoryInterface(firstCategoryInterface);
        return new FirstCategoryViewHolder(firstCategoryRowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull FirstCategoryViewHolder holder, int position) {

        Msg msg =getItem(position);
        holder.firstCategoryRowBinding.setMsg(msg);

    }

    public class FirstCategoryViewHolder extends RecyclerView.ViewHolder {

        FirstCategoryRowBinding firstCategoryRowBinding;

        public FirstCategoryViewHolder(FirstCategoryRowBinding binding)
        {
            super(binding.getRoot());
            this.firstCategoryRowBinding=binding;

        }

    }
    public interface FirstCategoryInterface
    {
        public void onFirstCategoryItemClick(String msgId);
    }

}
