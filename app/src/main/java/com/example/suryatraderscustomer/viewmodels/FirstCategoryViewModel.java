package com.example.suryatraderscustomer.viewmodels;

import com.example.suryatraderscustomer.models.Msg;
import com.example.suryatraderscustomer.repositories.FirstCategoryRepo;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FirstCategoryViewModel extends ViewModel {

    FirstCategoryRepo firstCategoryRepo=new FirstCategoryRepo();


    MutableLiveData<String> secondCategoryMsgMutableLiveData=new MutableLiveData<String>();

    public LiveData<List<Msg>> getFirstCategoryItems()
    {
        return firstCategoryRepo.getFirstCategoryItems();
    }


    public void setSecondCategoryList(String msgId)
    {
        secondCategoryMsgMutableLiveData.setValue(msgId);
    }
}
