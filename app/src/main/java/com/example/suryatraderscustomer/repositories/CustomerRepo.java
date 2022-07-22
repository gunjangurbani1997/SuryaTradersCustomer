package com.example.suryatraderscustomer.repositories;

import android.util.Log;
import android.widget.RelativeLayout;

import com.example.suryatraderscustomer.api.RetroFitClient;
import com.example.suryatraderscustomer.models.AuthenticationMsg;
import com.example.suryatraderscustomer.models.AuthenticationResponse;
import com.example.suryatraderscustomer.models.AuthenticationResult;
import com.example.suryatraderscustomer.models.CSRFToken;
import com.example.suryatraderscustomer.models.Msg;
import com.example.suryatraderscustomer.models.Original;
import com.example.suryatraderscustomer.models.Token;
import com.example.suryatraderscustomer.tokenmanager.TokenManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomerRepo {

        public MutableLiveData<Map<String,String>> ACCESS_TOKEN;
        public MutableLiveData<CSRFToken> CSRF_TOKEN=null;
        public HashMap<String,String> accessTokenMap=null;

        private static final String TAG="CustomerRepo";
        public LiveData<Map<String, String>> getAccessToken()
        {
           return ACCESS_TOKEN;
        }


    public LiveData<CSRFToken> getCSRFToken()
    {
       /* if (CSRF_TOKEN==null  )
        {*/
            CSRF_TOKEN=new MutableLiveData<>();
            CSRFToken();
     /*   }*/

        return CSRF_TOKEN;
    }

    public void CSRFToken()
    {
        Call<CSRFToken> csrfTokenCall= RetroFitClient.getInstance().getApi().getCSRFToken();
        csrfTokenCall.enqueue(new Callback<CSRFToken>() {

            @Override
            public void onResponse(Call<CSRFToken > call, Response<CSRFToken> response) {

                if (response.body()!=null) {

                    CSRFToken csrfToken=response.body();
                    CSRF_TOKEN.setValue(csrfToken);
                }
            }

            @Override
            public void onFailure(Call<CSRFToken> call, Throwable t)
            {
                android.util.Log.d(TAG, "onFailure:"+t.getCause());
                android.util.Log.d(TAG, "onFailure: failed to get CSRF Token");
            }

        });
    }

    public void login(final String mobileNo, String password, final MutableLiveData<Boolean> isSuccessful,final MutableLiveData<String>  authenticationFailure,String firebaseNotification) {

        Call<AuthenticationResponse> call= RetroFitClient.getInstance().getApi().login(mobileNo, password,firebaseNotification);
        call.enqueue(new Callback<AuthenticationResponse>() {

            @Override
            public void onResponse(Call<AuthenticationResponse> call, Response<AuthenticationResponse> response) {

                AuthenticationResponse authenticationResponse=response.body();
                AuthenticationResult authenticationResult=authenticationResponse.getResult();

                if(response.body().getStatus()==200) {
                    String customerId = authenticationResult.getCustomerId();
                    Token token = authenticationResult.getToken();
                    Original original = token.getOriginal();
                    String accessToken = original.getAccessToken();
                    accessTokenMap = new HashMap<String, String>();
                    accessTokenMap.put(customerId, accessToken);
                    ACCESS_TOKEN = new MutableLiveData<Map<String, String>>();
                    ACCESS_TOKEN.setValue(accessTokenMap);
                    isSuccessful.postValue(true);
                    Log.d(TAG, "onSuccess: customer login success " + response.code());
                }
                else
                {
                    String message=authenticationResult.getAuthenticationMsg();
                    authenticationFailure.postValue(message);
                    Log.d(TAG, "onFailure: customer login failure- "+message + response.body().getStatus());
                }

            }

            @Override
            public void onFailure(Call<AuthenticationResponse> call, Throwable t)
            {
                android.util.Log.d(TAG, "onFailure:"+t.getCause());
                android.util.Log.d(TAG, "onFailure: failed to login user ");
            }
        });

    }
    public void registerCustomer(String firstName, String lastName, String mobileNo, String password, String company,String address, final MutableLiveData<Boolean> isSuccessful, final MutableLiveData<String> registerMsg) {

        Call<AuthenticationResponse> call= RetroFitClient.getInstance().getApi().registerCustomer(firstName,lastName,mobileNo,password,company,address);
        call.enqueue(new Callback<AuthenticationResponse>() {

            @Override
            public void onResponse(Call<AuthenticationResponse> call, Response<AuthenticationResponse> response) {

                if (response.isSuccessful())
                {
                    AuthenticationResponse authenticationResponse=response.body();
                    AuthenticationResult authenticationResult=authenticationResponse.getResult();
                    String msg=authenticationResult.getAuthenticationMsg();
                    registerMsg.postValue(msg);
                    isSuccessful.postValue(true);
                    Log.d(TAG, "onSuccess: "+msg+" "+response.code());
                }

            }

            @Override
            public void onFailure(Call<AuthenticationResponse> call, Throwable t)
            {
                System.out.println(t.getCause());
                Log.d(TAG, "onFailure:"+t.getCause());
                Log.d(TAG, "onFailure: failed to register customer ");
            }
        });
    }


    public void changePassword( String currentPassword,String newPassword,final MutableLiveData<Boolean> isSuccessful, final MutableLiveData<String> changePasswordMsg)
    {
        Call<AuthenticationResponse> call= RetroFitClient.getInstance().getApi().changePassword(currentPassword,newPassword);
        call.enqueue(new Callback<AuthenticationResponse>() {

            @Override
            public void onResponse(Call<AuthenticationResponse> call, Response<AuthenticationResponse> response) {

                if (response.isSuccessful())
                {
                        AuthenticationResponse authenticationResponse = response.body();
                        AuthenticationResult authenticationResult = authenticationResponse.getResult();
                        String msg = authenticationResult.getAuthenticationMsg();
                        changePasswordMsg.postValue(msg);
                        isSuccessful.postValue(true);
                        Log.d(TAG, "onSuccess: change password success " + response.code());
                }
                else
                {
                    AuthenticationResponse authenticationResponse=response.body();
                    AuthenticationResult authenticationResult=authenticationResponse.getResult();
                    String msg=authenticationResult.getAuthenticationMsg();
                    changePasswordMsg.postValue(msg);
                    isSuccessful.postValue(false);
                    Log.d(TAG, "onFailure: failed to change password "+response.code());
                }
            }

            @Override
            public void onFailure(Call<AuthenticationResponse> call, Throwable t)
            {
                System.out.println(t.getCause());
                Log.d(TAG, "onFailure:"+t.getCause());
                Log.d(TAG, "onFailure: failed to change password ");
            }
        });
    }

    public void submitFeedbackForm( String subject,String message,final MutableLiveData<Boolean> isSuccessful, final MutableLiveData<String> changePasswordMsg)
    {
        Call<AuthenticationResponse> call= RetroFitClient.getInstance().getApi().submitFeedbackForm(subject,message);
        call.enqueue(new Callback<AuthenticationResponse>() {

            @Override
            public void onResponse(Call<AuthenticationResponse> call, Response<AuthenticationResponse> response) {

                if (response.isSuccessful())
                {
                    AuthenticationResponse authenticationResponse = response.body();
                    AuthenticationResult authenticationResult = authenticationResponse.getResult();
                    String msg = authenticationResult.getAuthenticationMsg();
                    changePasswordMsg.postValue(msg);
                    isSuccessful.postValue(true);
                    Log.d(TAG, "onResponse: "+msg+ " " + response.code());
                }
                else
                {
                    AuthenticationResponse authenticationResponse=response.body();
                    AuthenticationResult authenticationResult=authenticationResponse.getResult();
                    String msg=authenticationResult.getAuthenticationMsg();
                    changePasswordMsg.postValue(msg);
                    isSuccessful.postValue(false);
                    Log.d(TAG, "onFailure: Feedback Form submission failed "+response.code());
                }
            }

            @Override
            public void onFailure(Call<AuthenticationResponse> call, Throwable t)
            {
                System.out.println(t.getCause());
                Log.d(TAG, "onFailure:"+t.getCause());
                Log.d(TAG, "onFailure: Feedback Form submission failed ");
            }
        });
    }


}
