package com.example.suryatraderscustomer.viewmodels;

import android.util.Log;

import com.example.suryatraderscustomer.models.CSRFToken;
import com.example.suryatraderscustomer.models.Customer;
import com.example.suryatraderscustomer.repositories.CustomerRepo;

import java.util.Map;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CustomerViewModel extends ViewModel {

    CustomerRepo customerRepo=new CustomerRepo();
    private Customer customer;

    private static final String TAG="CustomerViewModel ";

    private MutableLiveData<Boolean> mIsSuccessful;
    private MutableLiveData<String> mAuthenticationFailure;
    private MutableLiveData<String> mChangePasswordMsg;

    //should be declared final becasue bindings only detect changes in the field's value, not of the field itself.
    public final MutableLiveData<String> mobileNo = new MutableLiveData<>();
    public final MutableLiveData<String> password = new MutableLiveData<>();

    public final MutableLiveData<String> current_password = new MutableLiveData<>();
    public final MutableLiveData<String> new_password = new MutableLiveData<>();
    public final MutableLiveData<String> confirm_password = new MutableLiveData<>();

    public final MutableLiveData<String> subject = new MutableLiveData<>();
    public final MutableLiveData<String> message = new MutableLiveData<>();


    public final MutableLiveData<String> errorMobileNo = new MutableLiveData<>();
    public final MutableLiveData<String> errorPassword = new MutableLiveData<>();


    public CustomerViewModel()
    {
        mIsSuccessful = new MutableLiveData<>();
        mAuthenticationFailure=new MutableLiveData<>();
        mChangePasswordMsg=new MutableLiveData<>();
    }

    private Boolean validateNewOldPassword()
    {
        boolean isValid = true;

        if (current_password.getValue() == null) {
            mChangePasswordMsg.setValue("Invalid current password!");
            isValid = false;

        }

        if (new_password.getValue() == null || new_password.getValue().length() < 6) {
            mChangePasswordMsg.setValue("Invalid new password");
            isValid = false;

        }

        if (confirm_password.getValue() == null || confirm_password.getValue().length() < 6) {
            mChangePasswordMsg.setValue("Invalid confirm password!");
            isValid = false;
        }

        if(confirm_password.getValue()!=null && new_password.getValue()!=null) {
            if (!(confirm_password.getValue().equals(new_password.getValue()))) {
                mChangePasswordMsg.setValue("New Password and Confirm Password do not match!");
                isValid = false;
            }
        }

        return isValid;

    }

    private Boolean validateFeedbackForm()
    {
        boolean isValid = true;

        if (subject.getValue() == null) {
            mChangePasswordMsg.setValue("Subject cannot be empty");
            isValid = false;
        }

        if (message.getValue() == null) {
            mChangePasswordMsg.setValue("Message cannot be empty!");
            isValid = false;
        }

        return isValid;

    }

    private boolean validateInputs()
    {
        boolean isValid = true;

        Log.d(TAG,"Mobile no "+mobileNo.getValue()+" Password "+password.getValue());

        if (mobileNo.getValue() == null || mobileNo.getValue().length()!=10) {
            mAuthenticationFailure.setValue("Invalid Mobile No");
            isValid = false;
        }

        if(password.getValue() == null)
        {
            mAuthenticationFailure.setValue("Password cannot be empty");
            isValid = false;
        }


        return isValid;
    }


    public void onLoginClick(String firebaseNotification) {
        if(validateInputs()) {
            customerRepo.login(mobileNo.getValue(), password.getValue(), mIsSuccessful,mAuthenticationFailure,firebaseNotification);
        } else {
            mIsSuccessful.postValue(false);
        }

    }

    public void changePassword() {
        if(validateNewOldPassword()) {
            customerRepo.changePassword(current_password.getValue(), new_password.getValue(), mIsSuccessful, mChangePasswordMsg);
        } else {
            mIsSuccessful.postValue(false);
        }

    }

    public void submitFeedbackForm() {
        if(validateFeedbackForm()) {
            customerRepo.submitFeedbackForm(subject.getValue(),message.getValue(),mIsSuccessful,mChangePasswordMsg);
        } else {
            mIsSuccessful.postValue(false);
        }

    }

    public  LiveData<Boolean> getIsSuccessful() {
        return mIsSuccessful;
    }

    public  LiveData<String> getChangePasswordMsg() {
        return mChangePasswordMsg;
    }

    public  LiveData<String> getAuthenticationFailure() {
        return mAuthenticationFailure;
    }


    public LiveData<Map<String,String>> getAccessToken() {
        return customerRepo.getAccessToken();
    }

    public LiveData<CSRFToken> getCSRFToken() {

        return customerRepo.getCSRFToken();
    }
}
