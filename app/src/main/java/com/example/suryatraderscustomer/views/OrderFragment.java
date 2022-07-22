package com.example.suryatraderscustomer.views;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.suryatraderscustomer.R;
import com.example.suryatraderscustomer.adapters.OrderAdapter;
import com.example.suryatraderscustomer.databinding.FragmentOrderBinding;
import com.example.suryatraderscustomer.models.Orders;
import com.example.suryatraderscustomer.viewmodels.OrderViewModel;

import java.util.List;


public class OrderFragment extends Fragment implements OrderAdapter.OrderInterface{

    FragmentOrderBinding fragmentOrderBinding;
    OrderViewModel orderViewModel;
    OrderAdapter orderAdapter;
    NavController navController;
  //  SubOrderViewModel subOrderViewModel;
    private static final String TAG="OrderFragment ";

    public OrderFragment() {

    }


    public static OrderFragment newInstance(String param1, String param2) {
        OrderFragment fragment = new OrderFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        fragmentOrderBinding= FragmentOrderBinding.inflate(inflater,container,false);
        return fragmentOrderBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        orderAdapter=new OrderAdapter(this);
        fragmentOrderBinding.orderRecyclerview.setAdapter(orderAdapter);
        orderViewModel=new ViewModelProvider(requireActivity()).get(OrderViewModel.class);
        orderViewModel.getOrder().observe(getViewLifecycleOwner(), new Observer<List<Orders>>() {
            @Override
            public void onChanged(List<Orders> orders) {
                orderAdapter.submitList(orders);
            }
        });
        navController= Navigation.findNavController(view);

    }

    @Override
    public void onOrderClick(Integer customerId,Integer orderId) {

        Log.d(TAG, "onOrderClicked "+customerId+" onOrderClick "+orderId);
        orderViewModel.setSubOrderList(customerId,orderId);
      /*  OrderFragmentDirections.ActionOrderFragmentToSubOrderFragment action = OrderFragmentDirections.actionOrderFragmentToSubOrderFragment(customerId,orderId);
        action.setCustomerId(customerId);
        action.setOrderId(orderId);*/
       /* navController.navigate(action);*/
    }

    public void onReviewClick(Integer orderId) {
    }
}