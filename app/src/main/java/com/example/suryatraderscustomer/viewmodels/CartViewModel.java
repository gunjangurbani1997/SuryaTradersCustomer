package com.example.suryatraderscustomer.viewmodels;

import com.example.suryatraderscustomer.models.GetCartMsg;
import com.example.suryatraderscustomer.models.SecondCategoryMsg;
import com.example.suryatraderscustomer.models.ThirdCategoryMsg;
import com.example.suryatraderscustomer.repositories.CartRepo;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CartViewModel extends ViewModel {

    CartRepo cartRepo=new CartRepo();
    private MutableLiveData<String> mAddToCartMsg;

    public CartViewModel() {
        mAddToCartMsg=new MutableLiveData<>();
    }

    public LiveData<List<GetCartMsg>> getCart()
    {
        return cartRepo.getCart();
    }

    public LiveData<Double> getCartTotal()
    {
        return cartRepo.getCartTotal();
    }

    public boolean addProductToCart(ThirdCategoryMsg thirdCategoryMsg, SecondCategoryMsg secondCategoryMsg) {
        cartRepo.addItemToCart(thirdCategoryMsg,secondCategoryMsg,mAddToCartMsg);
        return true;
    }

    public LiveData<String> getPlaceOrderMsg()
    {
        return cartRepo.getPlaceOrderMsg();
    }

    public void removeItemFromCart(String cartId)
    {
        cartRepo.removeItemFromCart(cartId);
    }

    public LiveData<String> getAddToCartMsg() {
        return mAddToCartMsg;
    }

}
