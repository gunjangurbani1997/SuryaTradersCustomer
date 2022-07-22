package com.example.suryatraderscustomer.views;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
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
import com.example.suryatraderscustomer.adapters.ThirdCategoryListAdapter;
import com.example.suryatraderscustomer.databinding.FragmentThirdCategoryBinding;
import com.example.suryatraderscustomer.models.ThirdCategoryMsg;
import com.example.suryatraderscustomer.viewmodels.CartViewModel;
import com.example.suryatraderscustomer.viewmodels.ThirdCategoryViewModel;

import java.util.List;

public class ThirdCategoryFragment extends Fragment implements ThirdCategoryListAdapter.ThirdCategoryInterface {

    private ThirdCategoryListAdapter thirdCategoryListAdapter;
    FragmentThirdCategoryBinding fragmentThirdCategoryBinding;
    private ThirdCategoryViewModel thirdCategoryViewModel;
    private CartViewModel cartViewModel;
    private NavController navController;

    private static final String TAG="ThirdCategoryFragment";

    public ThirdCategoryFragment() {

    }


    public static ThirdCategoryFragment newInstance(String param1, String param2) {
        ThirdCategoryFragment fragment =new ThirdCategoryFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        fragmentThirdCategoryBinding=FragmentThirdCategoryBinding.inflate(inflater,container,false);
        return  fragmentThirdCategoryBinding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String id=null;
        if (getArguments()!=null)
        {

            ThirdCategoryFragmentArgs args=ThirdCategoryFragmentArgs.fromBundle(getArguments());
            id=args.getSecondCategoryId();

        }
        thirdCategoryListAdapter=new ThirdCategoryListAdapter(this);
        fragmentThirdCategoryBinding.thirdCategoryRecyclerView.setAdapter(thirdCategoryListAdapter);

        thirdCategoryViewModel=new ViewModelProvider(requireActivity()).get(ThirdCategoryViewModel.class);
        thirdCategoryViewModel.getThirdCategoryMsgItems(id).observe(getViewLifecycleOwner(), new Observer<List<ThirdCategoryMsg>>() {
            @Override
            public void onChanged(List<ThirdCategoryMsg> thirdCategories) {
                thirdCategoryListAdapter.submitList(thirdCategories);
            }
        });
        navController= Navigation.findNavController(view);
    }


    @Override
    public void onThirdCategoryItemClick(ThirdCategoryMsg thirdCategoryMsg)
    {
        Log.d(TAG,"on item click");
        thirdCategoryViewModel.setThirdCategoryProduct(thirdCategoryMsg);
        ThirdCategoryFragmentDirections.ActionThirdCategoryFragmentToProductDetailFragment action=  ThirdCategoryFragmentDirections.actionThirdCategoryFragmentToProductDetailFragment(thirdCategoryMsg);
        action.setThirdCategoryMsg(thirdCategoryMsg);
        navController.navigate(action);
    }


}