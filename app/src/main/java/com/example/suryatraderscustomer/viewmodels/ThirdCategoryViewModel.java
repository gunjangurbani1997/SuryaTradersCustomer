package com.example.suryatraderscustomer.viewmodels;

import com.example.suryatraderscustomer.models.GetCartMsg;
import com.example.suryatraderscustomer.models.ThirdCategoryMsg;
import com.example.suryatraderscustomer.repositories.CartRepo;
import com.example.suryatraderscustomer.repositories.ThirdCategoryRepo;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ThirdCategoryViewModel extends ViewModel {


    ThirdCategoryRepo thirdCategoryRepo=new ThirdCategoryRepo();
    MutableLiveData<ThirdCategoryMsg> thirdCategoryMsgMutableLiveData=new MutableLiveData<>();

    public LiveData<List<ThirdCategoryMsg>> getThirdCategoryMsgItems(String thirdCategoryId)
    {
        return thirdCategoryRepo.getThirdCategoryitems(thirdCategoryId);
    }

    public void setThirdCategoryProduct(ThirdCategoryMsg thirdCategoryMsg)
    {
        thirdCategoryMsgMutableLiveData.setValue(thirdCategoryMsg);
    }

    public LiveData<ThirdCategoryMsg> getProduct()
    {
        return thirdCategoryMsgMutableLiveData;
    }

    CartRepo cartRepo=new CartRepo();

    public LiveData<List<GetCartMsg>> getCart()
    {
        return cartRepo.getCart();
    }


}
