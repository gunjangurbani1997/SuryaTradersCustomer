package com.example.suryatraderscustomer.views;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.suryatraderscustomer.R;
import com.example.suryatraderscustomer.adapters.CartListAdapter;
import com.example.suryatraderscustomer.adapters.FirstCategoryListAdapter;
import com.example.suryatraderscustomer.databinding.FragmentCartBinding;
import com.example.suryatraderscustomer.databinding.FragmentFirstCategoryBinding;
import com.example.suryatraderscustomer.models.Customer;
import com.example.suryatraderscustomer.models.GetCartMsg;
import com.example.suryatraderscustomer.models.Msg;
import com.example.suryatraderscustomer.tokenmanager.TokenManager;
import com.example.suryatraderscustomer.viewmodels.CartViewModel;
import com.example.suryatraderscustomer.viewmodels.FirstCategoryViewModel;

import java.util.List;


public class CartFragment extends Fragment implements CartListAdapter.CartInterface {
    private CartListAdapter cartListAdapter;
    FragmentCartBinding fragmentCartBinding;
    private CartViewModel cartViewModel;

    private NavController navController;
    private static final String TAG="CategoryFragment ";


    public CartFragment() {

    }

    public static CartFragment newInstance(String param1, String param2) {
        CartFragment fragment = new CartFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        fragmentCartBinding= FragmentCartBinding.inflate(inflater,container,false);
        return  fragmentCartBinding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        cartListAdapter=new CartListAdapter(this);

        fragmentCartBinding.cartRecyclerView.setAdapter(cartListAdapter);
        cartViewModel=new ViewModelProvider(requireActivity()).get(CartViewModel.class);

        cartViewModel.getCart().observe(getViewLifecycleOwner(), new Observer<List<GetCartMsg>>() {
            @Override
            public void onChanged(List<GetCartMsg> getCartItemList) {
                fragmentCartBinding.placeOrderButton.setEnabled(getCartItemList.size() > 0);
                if(getCartItemList.size() ==0)
                {
                    fragmentCartBinding.orderTotalTextView.setVisibility(View.INVISIBLE);
                    Toast.makeText(getActivity(),"Cart is empty!",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    cartListAdapter.submitList(getCartItemList);
                }
            }
        });

        cartViewModel.getCartTotal().observe(getViewLifecycleOwner(), new Observer<Double>() {
            @Override
            public void onChanged(Double cartTotal) {
                fragmentCartBinding.orderTotalTextView.setText("Total Rs: "+String.format(cartTotal.toString()));
            }
        });

        fragmentCartBinding.placeOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
                View titleView = getLayoutInflater().inflate(R.layout.order_confirm_title, null);
                final TextView order_confirm=new TextView(getContext());
                order_confirm.setPadding(20,20,20,20);
                order_confirm.setText("Are you sure you want to place this order?");
                order_confirm.setTextColor(Color.parseColor("#707070"));
                order_confirm.setTextSize(20);
                builder.setView(order_confirm);
                builder.setCustomTitle(titleView);
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (dialogInterface != null) {
                            placeOrder();

                        }
                    }
                });
                builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                builder.show();






            }
        });

        navController= Navigation.findNavController(view);
    }

    private void placeOrder() {

        cartViewModel.getPlaceOrderMsg().observe(getViewLifecycleOwner(),new Observer<String>() {
            @Override
            public void onChanged(String placeOrderMsg) {
                if (placeOrderMsg!=null && !placeOrderMsg.equals(""))
                {
                 //   navController.navigate(R.id.action_cartFragment_to_allOrderDetailsFragment);
                    Toast.makeText(getActivity(),placeOrderMsg,Toast.LENGTH_SHORT).show();
                    Log.d(TAG,"Order placed");
                }

            }
        });

    }

    @Override
    public void deleteCartItem(String cartId) {

        cartViewModel.removeItemFromCart(cartId);
        cartViewModel.getCart().observe(getViewLifecycleOwner(), new Observer<List<GetCartMsg>>() {
            @Override
            public void onChanged(List<GetCartMsg> getCartItemList) {
                cartListAdapter.submitList(getCartItemList);
                fragmentCartBinding.placeOrderButton.setEnabled(getCartItemList.size() > 0);
                if(getCartItemList.size() ==0)
                {
                    fragmentCartBinding.orderTotalTextView.setVisibility(View.INVISIBLE);
                    Toast.makeText(getActivity(),"Cart is empty!",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


}