package com.example.suryatraderscustomer.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.suryatraderscustomer.databinding.CartRowBinding;
import com.example.suryatraderscustomer.models.GetCartMsg;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class CartListAdapter extends ListAdapter<GetCartMsg, CartListAdapter.CartViewHolder> {

    private static final String TAG="CartListAdapter";
    CartInterface cartInterface;
    public CartListAdapter(CartInterface cartInterface) {
         super(GetCartMsg.itemCallback);
        this.cartInterface=cartInterface;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        CartRowBinding cartRowBinding=CartRowBinding.inflate(layoutInflater,parent,false);
        cartRowBinding.setCartInterface(cartInterface);
        return new CartViewHolder(cartRowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {

       GetCartMsg getCartMsg=getItem(position);
       holder.cartRowBinding.setCartItem(getCartMsg);
    }



    public class CartViewHolder extends RecyclerView.ViewHolder
    {
        CartRowBinding cartRowBinding;
        public CartViewHolder(@NonNull CartRowBinding cartRowBinding) {
            super(cartRowBinding.getRoot());
            this.cartRowBinding=cartRowBinding;
        }
    }

    public interface CartInterface
    {
        public void deleteCartItem(String cartId);
    }

}

