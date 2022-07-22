package com.example.suryatraderscustomer.repositories;

import android.util.Log;

import com.example.suryatraderscustomer.api.RetroFitClient;
import com.example.suryatraderscustomer.models.FirstCategory;
import com.example.suryatraderscustomer.models.Msg;
import com.example.suryatraderscustomer.models.Result;
import com.example.suryatraderscustomer.models.SecondCategory;
import com.example.suryatraderscustomer.models.SecondCategoryMsg;
import com.example.suryatraderscustomer.models.SecondCategoryResult;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SecondCategoryRepo {
    private MutableLiveData<List<SecondCategoryMsg>> mutableSecondCategoryList;


    private static final String TAG = "SecondCategoryRepo";

    public LiveData<List<SecondCategoryMsg>> getSecondCategoryItems(String firstCategoryId) {
//        if (mutableSecondCategoryList==null)
//        {
        mutableSecondCategoryList = new MutableLiveData<>();
        loadSecondCategoryItems(firstCategoryId);
        /*  }*/

        return mutableSecondCategoryList;
    }

    private void loadSecondCategoryItems( String firstCategoryId) {
        Call<FirstCategory> call = RetroFitClient.getInstance().getApi().getFirstCategoryChildren(firstCategoryId.toString());

        call.enqueue(new Callback<FirstCategory>() {
            @Override
            public void onResponse(Call<FirstCategory> call, Response<FirstCategory> response) {
                List<SecondCategoryMsg> secondCategoryMsgList = null;
                if (response.body().getStatus()==200) {

                    Log.d(TAG, "In loadSecondCategoryItems ");
                    System.out.println(response.body());

                    FirstCategory firstCategory = response.body();
                    Result result = firstCategory.getResult();
                    List<Msg> msgList = result.getMsg();
                    if (msgList.get(0).getSecondCategoryMsg() != null) {
                        secondCategoryMsgList = msgList.get(0).getSecondCategoryMsg();
                        Log.d(TAG, "Id " + secondCategoryMsgList.get(0).getId() + " " +
                                "Name " + secondCategoryMsgList.get(0).getName()
                                +"Category 1 Id "   +secondCategoryMsgList.get(0).getCat_1_id()+" Image "+secondCategoryMsgList.get(0).getImage());
                        mutableSecondCategoryList.setValue(secondCategoryMsgList);
                    } else {
                        Log.d(TAG, "Data not available");
                    }


                } else {
                    Log.d(TAG, "onFailure: failed to fetch Second category list from server");
                }
            }

            @Override
            public void onFailure(Call<FirstCategory> call, Throwable t) {
                Log.d(TAG, "onFailure: failed to Second category list from server");
            }
        });

    }


}
