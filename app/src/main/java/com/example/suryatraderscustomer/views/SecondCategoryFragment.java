package com.example.suryatraderscustomer.views;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.suryatraderscustomer.R;
import com.example.suryatraderscustomer.adapters.SecondCategoryListAdapter;
import com.example.suryatraderscustomer.databinding.FragmentSecondCategoryBinding;
import com.example.suryatraderscustomer.models.SecondCategoryMsg;
import com.example.suryatraderscustomer.repositories.SecondCategoryRepo;
import com.example.suryatraderscustomer.tokenmanager.TokenManager;
import com.example.suryatraderscustomer.viewmodels.SecondCategoryViewModel;

import java.util.List;



public class SecondCategoryFragment extends Fragment  implements SecondCategoryListAdapter.SecondCategoryInterface {

    private static final String TAG = "SecondCategoryFragment";

    private SecondCategoryListAdapter secondCategoryListAdapter;
    FragmentSecondCategoryBinding fragmentSecondCategoryBinding;
    private SecondCategoryViewModel secondCategoryViewModel;
    private NavController navController;
    TokenManager tokenManager;

    public SecondCategoryFragment() {

    }

    public static SecondCategoryFragment newInstance(String param1, String param2) {
        SecondCategoryFragment fragment = new SecondCategoryFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentSecondCategoryBinding = FragmentSecondCategoryBinding.inflate(inflater, container, false);
        return fragmentSecondCategoryBinding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String firstCategoryId = null;
        if (getArguments() != null) {

            SecondCategoryFragmentArgs args = SecondCategoryFragmentArgs.fromBundle(getArguments());
            firstCategoryId = args.getFirstCategoryId();

        }
        secondCategoryListAdapter = new SecondCategoryListAdapter(this);
        fragmentSecondCategoryBinding.secondCategoryRecyclerView.setAdapter(secondCategoryListAdapter);



        secondCategoryViewModel = new ViewModelProvider(requireActivity()).get(SecondCategoryViewModel.class);
        tokenManager=new TokenManager(getActivity());
        secondCategoryViewModel.getSecondCategoryMsgItems(firstCategoryId).observe(getViewLifecycleOwner(), new Observer<List<SecondCategoryMsg>>() {
            @Override
            public void onChanged(List<SecondCategoryMsg> secondCategories) {
                secondCategoryListAdapter.submitList(secondCategories);
            }
        });
        navController = Navigation.findNavController(view);

    }

    @Override
    public void onSecondCategoryItemClick(SecondCategoryMsg secondCategoryMsg) {

        Log.d(TAG, "onSecondCategoryItemClicked" + " Product Code " + secondCategoryMsg.getId() +
                " Parent Id " + secondCategoryMsg.getCat_1_id() + " Product Name " + secondCategoryMsg.getName());
        String secondCategoryMsgId = secondCategoryMsg.getId();

     if (secondCategoryMsg.getHasChildren()) {
            secondCategoryViewModel.setThirdCategoryList(secondCategoryMsgId);
            SecondCategoryFragmentDirections.ActionSecondCategoryFragmentToThirdCategoryFragment action = SecondCategoryFragmentDirections.actionSecondCategoryFragmentToThirdCategoryFragment(secondCategoryMsgId);
            action.setSecondCategoryId(secondCategoryMsgId);
            navController.navigate(action);
      }

        else
        {
            secondCategoryViewModel.setSecondCategoryProduct(secondCategoryMsg);
            SecondCategoryFragmentDirections.ActionSecondCategoryFragmentToSecondCategoryProductDetailFragment action = SecondCategoryFragmentDirections.actionSecondCategoryFragmentToSecondCategoryProductDetailFragment(secondCategoryMsg);
            action.setSecondCategoryMsg(secondCategoryMsg);
            navController.navigate(action);
        }
    }
}