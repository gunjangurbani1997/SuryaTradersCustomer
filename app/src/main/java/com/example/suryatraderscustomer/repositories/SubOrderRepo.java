package com.example.suryatraderscustomer.repositories;

import android.util.Log;

import com.example.suryatraderscustomer.api.RetroFitClient;
import com.example.suryatraderscustomer.models.SubOrderResponse;
import com.example.suryatraderscustomer.models.SubOrderResult;
import com.example.suryatraderscustomer.models.SubOrders;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubOrderRepo {

    private MutableLiveData<List<SubOrders>> mutableSubOrderList;

    private static final String TAG = "SubOrderRepository ";


    public LiveData<List<SubOrders>> getSubOrderList(Integer customerId, Integer orderId)
    {

        mutableSubOrderList=new MutableLiveData<>();
        loadSubOrderList(customerId,orderId);

        return mutableSubOrderList;
    }

    private void loadSubOrderList(Integer customerId,Integer orderId) {

        Call<SubOrderResponse> call= RetroFitClient.getInstance().getApi().getSubOrderList(orderId.toString());
        call.enqueue(new Callback<SubOrderResponse>() {

            @Override
            public void onResponse(Call<SubOrderResponse> call, Response<SubOrderResponse> response) {
                if (response.isSuccessful())
                {
                    Log.d(TAG,"SubOrder List ");
                    System.out.println(response.body());

                    SubOrderResponse subOrderResponse=response.body();
                    SubOrderResult subOrderResult=subOrderResponse.getSubOrderResponseResult();
                    List<SubOrders> subOrdersList=subOrderResult.getSubOrdersList();

                    Log.d(TAG,"Id "+  subOrdersList.get(0).getOrderId()+" "+"Name "+  subOrdersList.get(0).getProductName());
                    mutableSubOrderList.setValue(subOrdersList);
                }
                else
                {
                    Log.d(TAG, "onFailure: failed to fetch suborders list from server"+response.code());
                }
            }

            @Override
            public void onFailure(Call<SubOrderResponse> call, Throwable t)
            {
                System.out.println(t.getCause());
                Log.d(TAG, "onFailure:"+t.getCause());
                Log.d(TAG, "onFailure: failed to suborders list from server");
            }
        });

    }
}
