package com.example.suryatraderscustomer.views;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.suryatraderscustomer.R;
import com.example.suryatraderscustomer.databinding.FragmentSignUpBinding;
import com.example.suryatraderscustomer.models.Color;
import com.example.suryatraderscustomer.models.Customer;
import com.example.suryatraderscustomer.utils.Util;
import com.example.suryatraderscustomer.viewmodels.RegisterViewModel;

import java.util.List;


public class SignUpFragment extends Fragment {
    private RegisterViewModel registerViewModel;
    private NavController navController;
    private FrameLayout parentFrameLayout;
    FragmentSignUpBinding fragmentSignUpBinding;
    private static final String TAG="SignUpFragment ";

    public SignUpFragment() {}

    public static SignUpFragment newInstance(String param1, String param2) {
        SignUpFragment fragment = new SignUpFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        fragmentSignUpBinding= FragmentSignUpBinding.inflate(inflater,container,false);
        parentFrameLayout=getActivity().findViewById(R.id.register_frame_layout);

        fragmentSignUpBinding.alreadyUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setFragment(new SignInFragment());
            }
        });
        return  fragmentSignUpBinding.getRoot();


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        registerViewModel=new ViewModelProvider(requireActivity()).get(RegisterViewModel.class);
        fragmentSignUpBinding.setRegisterViewModel(registerViewModel);
        fragmentSignUpBinding.createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerViewModel.onRegisterCustomer();
                registerViewModel.getIsSuccessful().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
                    @Override
                    public void onChanged(@Nullable Boolean isSuccessful) {
                        if(isSuccessful)
                        {
                            registerViewModel.getRegisterMsg().observe(getViewLifecycleOwner(), new Observer<String>() {
                                @Override
                                public void onChanged(String registerMsg) {
                                    if(!registerMsg.contains("Success"))
                                    {
                                        Toast.makeText(getActivity(), registerMsg, Toast.LENGTH_SHORT).show();
                                        Log.d(TAG, "Msg: " + registerMsg);
                                    }
                                    else {
                                        final AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
                                        builder.setTitle("Alert!");

                                        final TextView pending_approval_msg=new TextView(getContext());
                                        pending_approval_msg.setPadding(20,10,20,10);
                                        pending_approval_msg.setTextSize(18);
                                        View titleView = getLayoutInflater().inflate(R.layout.layout_register_success_title, null);

                                        pending_approval_msg.setText("Successfully registered! Your approval is pending from Administrator. Please wait");
                                        builder.setCustomTitle(titleView);
                                        builder.setView(pending_approval_msg);

                                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                if (dialogInterface != null) {
                                                    dialogInterface.dismiss();
                                                }
                                            }
                                        });
                                        builder.show();

                                    }
                                }
                            });
                        }
                        else
                        {
                            registerViewModel.getRegisterMsg().observe(getViewLifecycleOwner(), new Observer<String>() {
                                @Override
                                public void onChanged(String registerMsg) {
                                    Toast.makeText(getActivity(),registerMsg,Toast.LENGTH_SHORT).show();
                                    Log.d(TAG,"Msg: "+registerMsg);
                                }
                            });

                        }
                    }
                });
            }
        });

    }


    private void setFragment(Fragment fragment) {

        FragmentTransaction fragmentTransaction=getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(parentFrameLayout.getId(),fragment);
        fragmentTransaction.commit();
    }
}