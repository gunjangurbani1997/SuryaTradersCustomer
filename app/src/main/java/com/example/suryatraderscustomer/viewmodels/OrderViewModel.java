package com.example.suryatraderscustomer.viewmodels;

import com.example.suryatraderscustomer.models.Orders;
import com.example.suryatraderscustomer.repositories.OrderRepo;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class OrderViewModel extends ViewModel {


    MutableLiveData<Integer> customerOrderListLiveData=new MutableLiveData<>();
    MutableLiveData<Integer> subOrderListLiveData=new MutableLiveData<>();
    MutableLiveData<Integer> reviewOrderListLiveData=new MutableLiveData<>();

    OrderRepo orderRepository=new OrderRepo();

    public LiveData<List<Orders>> getOrder()
    {
        return orderRepository.getOrder();
    }

    public void setSubOrderList(Integer customerId,Integer orderId)
    {
        subOrderListLiveData.setValue(customerId);
        subOrderListLiveData.setValue(orderId);
    }

    public void setReviewList(Integer orderId)
    {
        reviewOrderListLiveData.setValue(orderId);
    }
}
