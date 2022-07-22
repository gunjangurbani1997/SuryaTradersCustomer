package com.example.suryatraderscustomer.viewmodels;

import com.example.suryatraderscustomer.models.Msg;
import com.example.suryatraderscustomer.models.SecondCategoryMsg;
import com.example.suryatraderscustomer.repositories.SecondCategoryRepo;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SecondCategoryViewModel extends ViewModel {

    SecondCategoryRepo secondCategoryRepo=new SecondCategoryRepo();
    MutableLiveData<String> thirdCategoryMsgMutableLiveData=new MutableLiveData<>();
    MutableLiveData<SecondCategoryMsg> secondCategoryMsgMutableLiveData=new MutableLiveData<>();


    public LiveData<List<SecondCategoryMsg>> getSecondCategoryMsgItems(String firstCategoryId)
    {
        return secondCategoryRepo.getSecondCategoryItems(firstCategoryId);
    }

    public void setThirdCategoryList(String secondCategoryId)
    {
        thirdCategoryMsgMutableLiveData.setValue(secondCategoryId);
    }

    public void setSecondCategoryProduct(SecondCategoryMsg secondCategoryMsg)
    {
        secondCategoryMsgMutableLiveData.setValue(secondCategoryMsg);

    }

    public LiveData<SecondCategoryMsg> getProduct()
    {
        return secondCategoryMsgMutableLiveData;
    }

}
