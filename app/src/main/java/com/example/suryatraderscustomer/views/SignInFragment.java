package com.example.suryatraderscustomer.views;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.suryatraderscustomer.R;
import com.example.suryatraderscustomer.databinding.FragmentSignInBinding;
import com.example.suryatraderscustomer.models.CSRFToken;
import com.example.suryatraderscustomer.models.Customer;
import com.example.suryatraderscustomer.tokenmanager.TokenManager;
import com.example.suryatraderscustomer.utils.Util;
import com.example.suryatraderscustomer.viewmodels.CustomerViewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;


import java.util.Iterator;
import java.util.Map;


public class SignInFragment extends Fragment {

    private FrameLayout parentFrameLayout;
    private FragmentSignInBinding fragmentSignInBinding;
    private CustomerViewModel customerViewModel;
    private ProgressBar loginProgressBar;
    private static final String TAG="SignInFragment";
    String firebaseNotification;
    AlertDialog alertDialog;
    Customer customer;

    public SignInFragment() {}


    public static SignInFragment newInstance(String param1, String param2) {
        SignInFragment fragment = new SignInFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        fragmentSignInBinding= FragmentSignInBinding.inflate(inflater,container,false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        parentFrameLayout=getActivity().findViewById(R.id.register_frame_layout);

        fragmentSignInBinding.notUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setFragment(new SignUpFragment());
            }
        });
        return  fragmentSignInBinding.getRoot();


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() {
            @Override
            public void onComplete(@NonNull Task<String> task) {
                if (!task.isSuccessful()) {
                    Log.w(TAG, "Fetching FCM registration token failed", task.getException());
                    return;
                }
                firebaseNotification = task.getResult();
                customer=new Customer();
                customer.setNotification_id(firebaseNotification);
                Log.d(TAG,"Hello "+ firebaseNotification);

            }
        });
        customerViewModel=new ViewModelProvider(requireActivity()).get(CustomerViewModel.class);
        customerViewModel.getCSRFToken().observe(getViewLifecycleOwner(), new Observer<CSRFToken>() {
            @Override
            public void onChanged(CSRFToken csrfToken) {
                if (csrfToken != null)
                {
                    Log.d(TAG, "CSRF Token = " + csrfToken.getCsrf());
                }
                TokenManager tokenManager = new TokenManager(getActivity());
                tokenManager.createCSRFTokenSession(csrfToken);

            }
        });
        fragmentSignInBinding.setCustomerViewModel(customerViewModel);
        fragmentSignInBinding.btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentSignInBinding.failedIdentity.setVisibility(View.INVISIBLE);
                customerViewModel.onLoginClick(customer.getNotification_id());
                customerViewModel.getIsSuccessful().observe(getViewLifecycleOwner(), new Observer<Boolean>()
                {
                    @Override
                    public void onChanged(@Nullable Boolean isSuccessful) {
                        if(isSuccessful)
                        {
                            final AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
                            LayoutInflater inflater=getLayoutInflater();
                            builder.setView(inflater.inflate(R.layout.progress_bar_dialog,null));
                            builder.setCancelable(false);
                            alertDialog=builder.create();
                            alertDialog.show();
                            getAccessToken();
                        }
                        else {
                            customerViewModel.getAuthenticationFailure().observe(getViewLifecycleOwner(), new Observer<String>() {
                                @Override
                                public void onChanged(String message)
                                {
                                    Toast.makeText(getActivity(),message,Toast.LENGTH_SHORT).show();
                                }
                            });
                            fragmentSignInBinding.failedIdentity.setVisibility(View.VISIBLE);
                        }
                    }
                });

            }

        });
    }


    public void getAccessToken()
    {
        Handler handler=new Handler();
        handler.postDelayed(new Runnable()
        {
            @Override
            public void run() {
                alertDialog.dismiss();
            }
        },10000);
        customerViewModel.getAccessToken().observe(getViewLifecycleOwner(), new Observer<Map<String,String>>() {
            @Override
            public void onChanged(Map<String,String> accessToken) {
                String key=null;
                String value=null;
                if(accessToken!=null)
                {
                    Iterator entries = accessToken.entrySet().iterator();
                    while (entries.hasNext()) {
                        Map.Entry entry = (Map.Entry) entries.next();
                        key = (String)entry.getKey();
                        value = (String)entry.getValue();
                        Log.d(TAG,"Key = " + key + ", Value = " + value);
                    }
                    TokenManager tokenManager=new TokenManager(getActivity());
                    tokenManager.createSession(key,value);
                    Log.d(TAG,"Hello username "+TokenManager.getInstance().getUserName("KEY_USER_NAME", "def"));
                    Util.setIntent(getActivity(), CategoryOneActivity.class);
                }
            }
        });
    }





    private void setFragment(Fragment fragment) {

        FragmentTransaction fragmentTransaction=getActivity().getSupportFragmentManager().beginTransaction();
        /*  fragmentTransaction.setCustomAnimations(R.anim.slide_from_left,R.anim.slideout_from_right);*/
        fragmentTransaction.replace(parentFrameLayout.getId(),fragment);
        fragmentTransaction.commit();

    }


}