package com.example.suryatraderscustomer.views;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.suryatraderscustomer.R;
import com.example.suryatraderscustomer.adapters.SubOrderAdapter;
import com.example.suryatraderscustomer.databinding.FragmentSubOrderBinding;
import com.example.suryatraderscustomer.models.SubOrders;
import com.example.suryatraderscustomer.viewmodels.SubOrderViewModel;

import java.util.List;

public class SubOrderFragment extends Fragment {

    FragmentSubOrderBinding fragmentSubOrderBinding;
    SubOrderViewModel subOrderViewModel;
    SubOrderAdapter subOrderAdapter;


    public SubOrderFragment() {

    }


    public static SubOrderFragment newInstance(String param1, String param2) {
        SubOrderFragment fragment = new SubOrderFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        fragmentSubOrderBinding= FragmentSubOrderBinding.inflate(inflater,container,false);
        return fragmentSubOrderBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Integer customerId = null;
        Integer orderId = null;
        if (getArguments() != null) {

            SubOrderFragmentArgs args = SubOrderFragmentArgs.fromBundle(getArguments());
            customerId = args.getCustomerId();
            orderId=args.getOrderId();

        }

        subOrderAdapter = new SubOrderAdapter();
        fragmentSubOrderBinding.subOrderRecyclerview.setAdapter(subOrderAdapter);
        subOrderViewModel=new ViewModelProvider(requireActivity()).get(SubOrderViewModel.class);

        subOrderViewModel.getCustomerOrderDetailList(customerId,orderId).observe(getViewLifecycleOwner(), new Observer<List<SubOrders>>() {
            @Override
            public void onChanged(List<SubOrders> subOrdersList) {
                subOrderAdapter.submitList(subOrdersList);
            }
        });

    }
}