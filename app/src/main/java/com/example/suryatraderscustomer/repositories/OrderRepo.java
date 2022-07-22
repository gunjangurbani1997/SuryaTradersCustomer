package com.example.suryatraderscustomer.repositories;

import android.util.Log;

import com.example.suryatraderscustomer.api.RetroFitClient;
import com.example.suryatraderscustomer.models.OrderResponse;
import com.example.suryatraderscustomer.models.OrderResult;
import com.example.suryatraderscustomer.models.Orders;
import com.example.suryatraderscustomer.tokenmanager.TokenManager;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderRepo {

        private MutableLiveData<List<Orders>> mutableOrdersList=null;

        private static final String TAG = "OrderRepository ";
        private MutableLiveData<String> mutableErrorMsg = new MutableLiveData<>();


        public LiveData<List<Orders>> getOrder()
        {
           /* if (mutableOrdersList==null)
            {*/
                mutableOrdersList=new MutableLiveData<>();
                loadOrdersList();
          /*  }*/
            return mutableOrdersList;
        }

        private void loadOrdersList() {

            Call<OrderResponse> call= RetroFitClient.getInstance().getApi().getAllOrders();
            call.enqueue(new Callback<OrderResponse>() {

                @Override
                public void onResponse(Call<OrderResponse> call, Response<OrderResponse> response) {
                    if (response.isSuccessful())
                    {

                        if(response.isSuccessful()) {
                            OrderResponse orderResponse = response.body();
                            OrderResult orderResult = orderResponse.getOrderResponseResult();
                            List<Orders> orderList =new ArrayList<>();
                            String msg="";
                            Log.d(TAG, "Order List ");
                            System.out.println(response.body());
                            if (orderResult.getOrderList()!=null) {
                                orderList = orderResult.getOrderList();
                           //     Log.d(TAG, "Id " + orderList.get(0).getOrderId() + " " + "Name " + orderList.get(0).getCustomerId());
                                mutableOrdersList.setValue(orderList);
                                mutableErrorMsg.postValue("No orders found!");
                            }
                            else
                            {
                                msg=orderResult.getErrorMsg();
                                mutableErrorMsg.postValue(msg);
                            }


                        }
                        else
                        {
                            Log.d(TAG, "onFailure: failed to fetch order list from server"+response.code());
                        }
                    }

                }

                @Override
                public void onFailure(Call<OrderResponse> call, Throwable t)
                {
                    System.out.println(t.getCause());
                    Log.d(TAG, "onFailure:"+t.getCause());
                    Log.d(TAG, "onFailure: failed to  fetch order list from server");
                }
            });
        }

}
