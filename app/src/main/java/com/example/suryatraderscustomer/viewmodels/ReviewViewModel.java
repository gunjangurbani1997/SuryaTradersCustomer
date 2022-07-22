package com.example.suryatraderscustomer.viewmodels;

import android.util.Log;


import com.example.suryatraderscustomer.models.Review;
import com.example.suryatraderscustomer.repositories.ReviewRepo;
import java.util.List;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ReviewViewModel extends ViewModel {

    ReviewRepo reviewRepo=new ReviewRepo();

    public final MutableLiveData<String> reviewTextArea = new MutableLiveData<>();

    public LiveData<List<Review>> getReviews(Integer orderId)
    {
        return reviewRepo.getReviewOfOrder(orderId);
    }

    public void submitReview(Integer orderId,String msg)
    {
         reviewRepo.submitReview(orderId,msg);
    }

}
