package com.example.suryatraderscustomer.repositories;

import android.util.Log;
import android.widget.Toast;

import com.example.suryatraderscustomer.api.RetroFitClient;
import com.example.suryatraderscustomer.models.FirstCategory;
import com.example.suryatraderscustomer.models.GetCartMsg;
import com.example.suryatraderscustomer.models.CartResponse;
import com.example.suryatraderscustomer.models.CartResult;
import com.example.suryatraderscustomer.models.GetCartResponse;
import com.example.suryatraderscustomer.models.GetCartResult;
import com.example.suryatraderscustomer.models.Msg;
import com.example.suryatraderscustomer.models.PlaceOrderResponse;
import com.example.suryatraderscustomer.models.PlaceOrderResult;
import com.example.suryatraderscustomer.models.RemoveCartItemResponse;
import com.example.suryatraderscustomer.models.RemoveCartItemResult;
import com.example.suryatraderscustomer.models.ReqData;
import com.example.suryatraderscustomer.models.Result;
import com.example.suryatraderscustomer.models.SecondCategoryMsg;
import com.example.suryatraderscustomer.models.ThirdCategoryMsg;
import com.example.suryatraderscustomer.tokenmanager.TokenManager;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;


import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartRepo {


    private static final String TAG = "CartRepo ";
    private MutableLiveData<List<GetCartMsg>> mutableCart = new MutableLiveData<>();
    private MutableLiveData<Double> mutableCartTotal = new MutableLiveData<>();
    private MutableLiveData<String> mutablePlaceOrderMsg = new MutableLiveData<>();
    private MutableLiveData<String> mutableErrorMsg = new MutableLiveData<>();
    List<String> cartIdList=new ArrayList<>();

    public LiveData<List<GetCartMsg>> getCart() {
        /*if (mutableCart.getValue() == null) {*/
            initCart();
     /*   }*/
        return mutableCart;

    }

    public LiveData<Double> getCartTotal() {
        return mutableCartTotal;
    }


    public void initCart() {

        Call<GetCartResponse> call= RetroFitClient.getInstance().getApi().getAllCartItems(TokenManager.getInstance().getUserName("KEY_USER_NAME", "customerId"));
        call.enqueue(new Callback<GetCartResponse>() {

            @Override
            public void onResponse(Call<GetCartResponse> call, Response<GetCartResponse> response) {
                if (response.isSuccessful()) {

                        Log.d(TAG, "In initCart method");
                        System.out.println(response.body());

                        if (response.isSuccessful()){
                            GetCartResponse getCartResponse = response.body();
                            GetCartResult getCartResult = getCartResponse.getGetCartResult();
                            List<GetCartMsg> getCartList = new ArrayList<>();
                            String msg="";
                            if (getCartResult.getCartList() != null) {
                                getCartList = getCartResult.getCartList();
                                if(getCartResult.getCartTotal()!=null) {
                                    mutableCartTotal.setValue(getCartResult.getCartTotal());
                                    mutableErrorMsg.postValue("Cart is Empty!");
                                }
                                mutableCart.setValue(getCartList);
                                //    Log.d(TAG, "Id " + getCartList.get(0).getCartId() + " " + "Product Name " + getCartList.get(0).getProduct_code());

                            }
                            else {
                                msg = getCartResult.getErrorMsg();
                                mutableErrorMsg.postValue(msg);
                                Log.d(TAG, "onFailure: failed to fetch cart list from server " +msg+" "+ response.body().getStatus());
                            }
                         }

                }

            }

            @Override
            public void onFailure(Call<GetCartResponse> call, Throwable t)
            {
                System.out.println(t.getCause());
                Log.d(TAG, "onFailure:"+t.getCause());
                Log.d(TAG, "onFailure: failed to fetch cart list from server");
            }
        });

    }

    public void addItemToCart(ThirdCategoryMsg thirdCategoryMsg, SecondCategoryMsg secondCategoryMsg,final MutableLiveData<String> mAddToCartMsg) {
      List<ReqData> reqDataList=new ArrayList<>();

      String catLevel;
      String productId;

      if(thirdCategoryMsg!=null) {
          productId=thirdCategoryMsg.getId();
          catLevel="3";
          for (int i = 0; i < thirdCategoryMsg.getUnitColorQuantityList().size(); i++) {
              ReqData reqData = new ReqData();
              reqData.setColor(thirdCategoryMsg.getUnitColorQuantityList().get(i).getColor().getLabel());
              reqData.setUnit(thirdCategoryMsg.getUnitColorQuantityList().get(i).getUnits());
              reqData.setQuantity(thirdCategoryMsg.getUnitColorQuantityList().get(i).getQuantity());
              reqDataList.add(reqData);
          }
      }
      else
      {
          catLevel="2";
          productId=secondCategoryMsg.getId();
          for (int i = 0; i < secondCategoryMsg.getUnitColorQuantityList().size(); i++) {
              ReqData reqData = new ReqData();
              reqData.setColor(secondCategoryMsg.getUnitColorQuantityList().get(i).getColor().getLabel());
              reqData.setUnit(secondCategoryMsg.getUnitColorQuantityList().get(i).getUnits());
              reqData.setQuantity(secondCategoryMsg.getUnitColorQuantityList().get(i).getQuantity());
              reqDataList.add(reqData);
          }
      }

        Gson gson = new GsonBuilder().create();
        String jsonElements = gson.toJson(reqDataList);
        Log.d(TAG,"Json elements"+jsonElements);

        Call<CartResponse> call = RetroFitClient.getInstance().getApi().addToCart(TokenManager.getInstance().getUserName("KEY_USER_NAME", "def"), catLevel, productId, jsonElements);
        call.enqueue(new Callback<CartResponse>() {
            @Override
            public void onResponse(Call<CartResponse> call, Response<CartResponse> response) {

                Log.d("onResponse", "" + response.code());

                if (response.isSuccessful())
                {
                    CartResponse cartResponse;
                    CartResult cartResult;
                    String cartMsg="" ;

                    if (response.body().getStatus() == 200) {

                         cartResponse = response.body();
                         cartResult = cartResponse.getCartResult();
                         cartMsg = cartResult.getCartMsg();
                         mAddToCartMsg.postValue(cartMsg);


                        Log.d("onResponse Cart add ", cartMsg + " " + response.body().getStatus());
                    }
                    else
                        {
                             mAddToCartMsg.postValue(cartMsg);
                        }
                }
            }

            @Override
            public void onFailure(Call<CartResponse> call, Throwable t) {
                Log.d("onFailure", "" + t.getMessage());
            }
        });
    }


    public LiveData<String> getPlaceOrderMsg() {

        if(mutableCart.getValue() == null) {
            mutablePlaceOrderMsg.setValue("Cart is empty");
            return mutablePlaceOrderMsg ;
        }
        else {

            List<GetCartMsg> getCartList = mutableCart.getValue();
            for (GetCartMsg cartItem : getCartList) {
                cartIdList.add(cartItem.getCartId().toString());
            }
            if (cartIdList.size() != 0 && cartIdList.size() > 0) {
                placeOrder(cartIdList);
            }

            return mutablePlaceOrderMsg;
        }

    }

    public void placeOrder(List<String> cartIdList) {

        Call<PlaceOrderResponse> call = RetroFitClient.getInstance().getApi().placeOrder();
        call.enqueue(new Callback<PlaceOrderResponse>() {
            @Override
            public void onResponse(Call<PlaceOrderResponse> call, Response<PlaceOrderResponse> response) {

                Log.d("onResponse", "" + response.code());

                if (response.isSuccessful()) {
                    PlaceOrderResponse placeOrderResponse;
                    PlaceOrderResult placeOrderResult;
                    String placeOrderMsg="";

                    if(response.body().getStatus()==200)
                    {
                        placeOrderResponse = response.body();
                        placeOrderResult = placeOrderResponse.getResult();
                        placeOrderMsg = placeOrderResult.getPlaceOrderMsg();
                        mutablePlaceOrderMsg.setValue(placeOrderMsg);
                        Log.d("onSuccess: Place Order", placeOrderMsg + " " + response.body().getStatus());
                        initCart();
                    }
                    else
                    {
                        mutablePlaceOrderMsg.setValue(placeOrderMsg);
                        Log.d("onFailure: Place Order", placeOrderMsg + " " + response.body().getStatus());
                    }

                }
            }

            @Override
            public void onFailure(Call<PlaceOrderResponse> call, Throwable t) {
                Log.d("onFailure", "" + t.getMessage());
            }
        });

    }

    public void removeItemFromCart(final String cartId)
    {
        Call<RemoveCartItemResponse> call= RetroFitClient.getInstance().getApi().removeItemFromCart(cartId);
        call.enqueue(new Callback<RemoveCartItemResponse>() {

            @Override
            public void onResponse(Call<RemoveCartItemResponse> call, Response<RemoveCartItemResponse> response) {

                if (response.isSuccessful()) {
                    RemoveCartItemResponse removeCartItemResponse;
                    RemoveCartItemResult removeCartItemResult;
                    String removeItemFromCartMsg="";
                    if(response.body().getStatus()==200) {
                        removeCartItemResponse = response.body();
                        removeCartItemResult = removeCartItemResponse.getResult();
                        removeItemFromCartMsg = removeCartItemResult.getRemoveItemFromCartMsg();
                        Log.d(TAG, "onSuccess: remove item from cart" + response.body().getStatus() +" "+ removeItemFromCartMsg);
                        initCart();
                    }
                    else {
                        mutableErrorMsg.postValue(removeItemFromCartMsg);
                        Log.d(TAG, "onFailure: remove item from cart "+response.body().getStatus());
                    }
                }
            }

            @Override
            public void onFailure(Call<RemoveCartItemResponse> call, Throwable t) {
                System.out.println(t.getCause());
                Log.d(TAG, "onFailure:" + t.getCause());
                Log.d(TAG, "onFailure: remove item from cart ");
            }


        });
    }

}
