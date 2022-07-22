package com.example.suryatraderscustomer.viewmodels;

import com.example.suryatraderscustomer.models.SubOrders;
import com.example.suryatraderscustomer.repositories.SubOrderRepo;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class SubOrderViewModel extends ViewModel {

    SubOrderRepo subOrderRepository=new SubOrderRepo();
    public LiveData<List<SubOrders>> getCustomerOrderDetailList(Integer customerId, Integer orderId)
    {
        return subOrderRepository.getSubOrderList(customerId,orderId);
    }
}
