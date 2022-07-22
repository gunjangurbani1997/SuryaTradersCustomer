package com.example.suryatraderscustomer.views;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.suryatraderscustomer.R;
import com.example.suryatraderscustomer.databinding.FragmentChangePasswordBinding;
import com.example.suryatraderscustomer.databinding.FragmentFeedbackFormBinding;
import com.example.suryatraderscustomer.viewmodels.CustomerViewModel;


public class FeedbackFormFragment extends Fragment {

    private NavController navController;
    private CustomerViewModel customerViewModel;
    private FragmentFeedbackFormBinding fragmentFeedbackFormBinding;

    public FeedbackFormFragment() {

    }


    public static FeedbackFormFragment newInstance(String param1, String param2) {
        FeedbackFormFragment fragment = new FeedbackFormFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragmentFeedbackFormBinding= FragmentFeedbackFormBinding.inflate(inflater,container,false);
        return  fragmentFeedbackFormBinding.getRoot();
    }
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        customerViewModel=new ViewModelProvider(requireActivity()).get(CustomerViewModel.class);
        fragmentFeedbackFormBinding.setCustomerViewModel(customerViewModel);

        fragmentFeedbackFormBinding.btnFeedbackForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customerViewModel.submitFeedbackForm();
                customerViewModel.getChangePasswordMsg().observe(getViewLifecycleOwner(), new Observer<String>()
                {
                    public void onChanged(String message)
                    {
                        if(message.contains("submitted")) {
                            fragmentFeedbackFormBinding.feedbackSubject.getText().clear();
                            fragmentFeedbackFormBinding.feedbackMessage.getText().clear();
                        }
                        Toast.makeText(getActivity(),message,Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        navController = Navigation.findNavController(view);
    }
}