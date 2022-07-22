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
import android.view.WindowManager;

import com.example.suryatraderscustomer.R;
import com.example.suryatraderscustomer.adapters.FirstCategoryListAdapter;
import com.example.suryatraderscustomer.databinding.FragmentFirstCategoryBinding;
import com.example.suryatraderscustomer.models.Msg;
import com.example.suryatraderscustomer.tokenmanager.TokenManager;
import com.example.suryatraderscustomer.viewmodels.FirstCategoryViewModel;
import com.example.suryatraderscustomer.viewmodels.SecondCategoryViewModel;

import java.util.List;


public class FirstCategoryFragment extends Fragment implements FirstCategoryListAdapter.FirstCategoryInterface {
    private FirstCategoryListAdapter firstCategoryListAdapter;
    FragmentFirstCategoryBinding fragmentFirstCategoryBinding;
    TokenManager tokenManager;

    private FirstCategoryViewModel firstCategoryViewModel;
    private SecondCategoryViewModel secondCategoryViewModel;
    private NavController navController;
    private static final String TAG="FirstCategoryFragment";


    public static FirstCategoryFragment newInstance(String param1, String param2) {
        FirstCategoryFragment fragment = new FirstCategoryFragment();
        return fragment;
    }

    public FirstCategoryFragment()
    {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragmentFirstCategoryBinding=FragmentFirstCategoryBinding.inflate(inflater,container,false);
        return  fragmentFirstCategoryBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        firstCategoryListAdapter=new FirstCategoryListAdapter(this);
        fragmentFirstCategoryBinding.firstCategoryRecyclerView.setAdapter(firstCategoryListAdapter);

        firstCategoryViewModel=new ViewModelProvider(requireActivity()).get(FirstCategoryViewModel.class);
        tokenManager=new TokenManager(getActivity());
        firstCategoryViewModel.getFirstCategoryItems().observe(getViewLifecycleOwner(), new Observer<List<Msg>>() {
            @Override
            public void onChanged(List<Msg> firstCategories) {
                firstCategoryListAdapter.submitList(firstCategories);
            }
        });
        navController= Navigation.findNavController(view);

    }


    @Override
    public void onFirstCategoryItemClick(String msgId) {

        Log.d(TAG,"onFirstCategoryItemClicked");

        firstCategoryViewModel.setSecondCategoryList(msgId);
        FirstCategoryFragmentDirections.ActionFirstCategoryFragmentToSecondCategoryFragment action=FirstCategoryFragmentDirections.actionFirstCategoryFragmentToSecondCategoryFragment(msgId);
        action.setFirstCategoryId(msgId);
        navController.navigate(action);
    }
}