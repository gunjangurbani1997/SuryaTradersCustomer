package com.example.suryatraderscustomer.views;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.suryatraderscustomer.R;
import com.example.suryatraderscustomer.adapters.OrderAdapter;
import com.example.suryatraderscustomer.databinding.FragmentAllOrderDetailsBinding;
import com.example.suryatraderscustomer.models.Orders;
import com.example.suryatraderscustomer.tokenmanager.TokenManager;
import com.example.suryatraderscustomer.utils.Util;
import com.example.suryatraderscustomer.viewmodels.OrderViewModel;

import java.util.List;


public class AllOrderDetailsFragment extends Fragment implements OrderAdapter.OrderInterface {
    FragmentAllOrderDetailsBinding fragmentAllOrderDetailsBinding;
    OrderAdapter orderAdapter;
    OrderViewModel orderViewModel;
    public static final String TAG="AllOrderDetailsFragment";
    NavController navController;

    public AllOrderDetailsFragment() {}

    public static AllOrderDetailsFragment newInstance(String param1, String param2) {
        AllOrderDetailsFragment fragment = new AllOrderDetailsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        fragmentAllOrderDetailsBinding=FragmentAllOrderDetailsBinding.inflate(inflater,container,false);
        return fragmentAllOrderDetailsBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        orderAdapter = new OrderAdapter(this);
        fragmentAllOrderDetailsBinding.orderRecyclerview.setAdapter(orderAdapter);
        orderViewModel=new ViewModelProvider(requireActivity()).get(OrderViewModel.class);
        orderViewModel.getOrder().observe(getViewLifecycleOwner(), new Observer<List<Orders>>() {
            @Override
            public void onChanged(List<Orders> customerOrderDetails) {
                if(customerOrderDetails.size()>0) {
                    orderAdapter.submitList(customerOrderDetails);
                }
                else
                {
                    Toast.makeText(getActivity(),"No orders found!",Toast.LENGTH_LONG).show();
                }
            }
        });

        fragmentAllOrderDetailsBinding.paymentDetailsPopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final View paymentView=getLayoutInflater().inflate(R.layout.payment_details_alert_box,null,false);

                final AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
                View titleView = getLayoutInflater().inflate(R.layout.layout_alert_box_title, null);
                builder.setCustomTitle(titleView);
                builder.setView(paymentView);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
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

    @Override
    public void onOrderClick(Integer customerId,Integer orderId) {
        Log.d(TAG, "onOrderClicked "+customerId+" onOrderClick "+orderId);
        customerId= Integer.parseInt(TokenManager.getInstance().getUserName("KEY_USER_NAME", "customerId"));
        orderViewModel.setSubOrderList(customerId,orderId);
        AllOrderDetailsFragmentDirections.ActionAllOrderDetailsFragmentToSubOrderFragment action = AllOrderDetailsFragmentDirections.actionAllOrderDetailsFragmentToSubOrderFragment(customerId,orderId);
        action.setCustomerId(customerId);
        action.setOrderId(orderId);
        navController.navigate(action);
    }

    @Override
    public void onReviewClick(Integer orderId) {
        Log.d(TAG,"onReviewClicked ");

        orderViewModel.setReviewList(orderId);
        AllOrderDetailsFragmentDirections.ActionAllOrderDetailsFragmentToReviewFragment action= AllOrderDetailsFragmentDirections.actionAllOrderDetailsFragmentToReviewFragment(orderId);
        action.setOrderId(orderId);
        navController.navigate(action);
    }


}