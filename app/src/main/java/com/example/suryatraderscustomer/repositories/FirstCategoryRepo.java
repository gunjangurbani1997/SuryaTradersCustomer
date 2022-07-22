package com.example.suryatraderscustomer.repositories;

import android.util.Log;

import com.example.suryatraderscustomer.api.RetroFitClient;
import com.example.suryatraderscustomer.models.FirstCategory;
import com.example.suryatraderscustomer.models.Msg;
import com.example.suryatraderscustomer.models.Result;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FirstCategoryRepo {

    private MutableLiveData<List<Msg>> mutableFirstCategoryList;
    private static final String TAG = "FirstCategoryRepo";


    public LiveData<List<Msg>> getFirstCategoryItems()
    {
       /* if (mutableFirstCategoryList==null)
        {*/
            mutableFirstCategoryList=new MutableLiveData<>();
            loadFirstCategoryItems();
      /*  }*/
        return mutableFirstCategoryList;
    }

    private void loadFirstCategoryItems()
    {
       Call<FirstCategory> call= RetroFitClient.getInstance().getApi().getFirstCategoryListItems();
       call.enqueue(new Callback<FirstCategory>() {

            @Override
            public void onResponse(Call<FirstCategory> call, Response<FirstCategory> response) {
                if (response.body().getStatus()==200)
                {
                    Log.d(TAG,"In loadFirstCategoryItems");
                    System.out.println(response.body());

                    FirstCategory firstCategory=response.body();
                    Result result=firstCategory.getResult();
                    List<Msg> msgList=result.getMsg();

                    Log.d(TAG,"Id "+  msgList.get(0).getId()+" "+"Name "+  msgList.get(0).getName());
                    Log.d(TAG,"Id "+  msgList.get(0).getId()+" "+"Name "+  msgList.get(0).getImage());

                    mutableFirstCategoryList.setValue(msgList);
                }
                else
                {
                    Log.d(TAG, "onFailure: failed to fetch first category list from server "+response.body().getStatus());
                }
            }

            @Override
            public void onFailure(Call<FirstCategory> call, Throwable t)
            {
                System.out.println(t.getCause());
                Log.d(TAG, "onFailure:"+t.getCause());
                Log.d(TAG, "onFailure: failed to fetch first category list from server");
            }
        });

    }


}
