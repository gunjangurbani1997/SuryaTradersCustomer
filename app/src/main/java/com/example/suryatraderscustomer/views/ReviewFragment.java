package com.example.suryatraderscustomer.views;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.suryatraderscustomer.adapters.ReviewAdapter;
import com.example.suryatraderscustomer.databinding.FragmentReviewBinding;
import com.example.suryatraderscustomer.models.Review;
import com.example.suryatraderscustomer.viewmodels.ReviewViewModel;

import java.util.List;

public class ReviewFragment extends Fragment implements ReviewAdapter.ReviewInterface {


    FragmentReviewBinding fragmentReviewBinding;
    ReviewAdapter reviewAdapter;
    ReviewViewModel reviewModel;
    Integer orderId;

    private static final String TAG="ReviewFragment ";

    public ReviewFragment() {

    }


    public static ReviewFragment newInstance(String param1, String param2) {
        ReviewFragment fragment = new ReviewFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentReviewBinding= FragmentReviewBinding.inflate(inflater,container,false);
        if (getArguments() != null)
        {

            ReviewFragmentArgs args = ReviewFragmentArgs.fromBundle(getArguments());
            orderId = args.getOrderId();

        }
        return  fragmentReviewBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        reviewAdapter=new ReviewAdapter(this);
        fragmentReviewBinding.reviewRecyclerView.setAdapter(reviewAdapter);
        reviewModel=new ViewModelProvider(requireActivity()).get(ReviewViewModel.class);

        reviewModel.getReviews(orderId).observe(getViewLifecycleOwner(), new Observer<List<Review>>() {
            @Override
            public void onChanged(List<Review> reviews) {
                reviewAdapter.submitList(reviews);
            }
        });


        fragmentReviewBinding.sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG,fragmentReviewBinding.reviewTextArea.getText().toString());
                String msg=fragmentReviewBinding.reviewTextArea.getText().toString();
                reviewModel.submitReview(orderId,msg);
            }
        });
    }

    public void submitReview(Integer orderId)
    {

    }

}