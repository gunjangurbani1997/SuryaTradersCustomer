package com.example.suryatraderscustomer.repositories;

import android.util.Log;

import com.example.suryatraderscustomer.api.RetroFitClient;
import com.example.suryatraderscustomer.models.SecondCategory;
import com.example.suryatraderscustomer.models.SecondCategoryMsg;
import com.example.suryatraderscustomer.models.SecondCategoryResult;
import com.example.suryatraderscustomer.models.ThirdCategoryMsg;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ThirdCategoryRepo {

    private MutableLiveData<List<ThirdCategoryMsg>> mutableThirdCategoryList;
    private static final String TAG = "ThirdCategoryRepo";

    public LiveData<List<ThirdCategoryMsg>> getThirdCategoryitems(String id)
    {
//        if (mutableSecondCategoryList==null)
//        {
        mutableThirdCategoryList=new MutableLiveData<>();
        loadThirdCategoryItems(id);
        /*  }*/

        return mutableThirdCategoryList;
    }

    private void loadThirdCategoryItems(final String id)
    {

        Call<SecondCategory> call= RetroFitClient.getInstance().getApi().getSecondCategoryChildren(id);
        call.enqueue(new Callback<SecondCategory>() {
            @Override
            public void onResponse(Call<SecondCategory> call, Response<SecondCategory> response) {
                List<ThirdCategoryMsg> thirdCategoryMsgList=null;
                if (response.body().getStatus()==200)
                {
                    Log.d(TAG,"In loadThirdCategoryItems method");
                    System.out.println(response.body());
                    SecondCategory secondCategory=response.body();
                    SecondCategoryResult secondCategoryResult=secondCategory.getResult();
                    List<SecondCategoryMsg> secondCategoryMsgList=secondCategoryResult.getSecondCategoryMsg();
                    if(secondCategoryMsgList.get(0).getThirdCategoryMsg()!=null)
                    {
                        thirdCategoryMsgList = secondCategoryMsgList.get(0).getThirdCategoryMsg();
                        Log.d(TAG,"Id "+  secondCategoryMsgList.get(0).getId()+" "+"Name "+  secondCategoryMsgList.get(0).getName());
                        mutableThirdCategoryList.setValue(thirdCategoryMsgList);
                    }
                    else
                    {
                        Log.d(TAG,"Data not available "+response.body().getStatus() );
                    }

                }
                else
                {
                    Log.d(TAG, "onFailure: failed to fetch Third CategoryItems from server "+response.body().getStatus());
                }
            }

            @Override
            public void onFailure(Call<SecondCategory> call, Throwable t)
            {
                Log.d(TAG, "onFailure: failed to ThirdCategoryItems from server");
            }
        });

    }

}
