package com.example.suryatraderscustomer.viewmodels;

import com.example.suryatraderscustomer.models.Customer;
import com.example.suryatraderscustomer.repositories.CustomerRepo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RegisterViewModel extends ViewModel {

    CustomerRepo customerRepo=new CustomerRepo();
    private Customer customer;

    private MutableLiveData<Boolean> mIsRegisterSuccessful;
    private MutableLiveData<String> mRegisterMsg;


    //should be declared final becasue bindings only detect changes in the field's value, not of the field itself.
    public final MutableLiveData<String> firstName = new MutableLiveData<>();
    public final MutableLiveData<String> lastName = new MutableLiveData<>();
    public final MutableLiveData<String> mobileNo = new MutableLiveData<>();
    public final MutableLiveData<String> password = new MutableLiveData<>();
    public final MutableLiveData<String> company = new MutableLiveData<>();
    public final MutableLiveData<String> address = new MutableLiveData<>();


    public final MutableLiveData<String> errorFirstName = new MutableLiveData<>();
    public final MutableLiveData<String> errorLastName = new MutableLiveData<>();
    public final MutableLiveData<String> errorCompany = new MutableLiveData<>();
    public final MutableLiveData<String> errorMobileNo = new MutableLiveData<>();
    public final MutableLiveData<String> errorPassword = new MutableLiveData<>();
    public final MutableLiveData<String> errorAddress = new MutableLiveData<>();


    public RegisterViewModel() {
        mIsRegisterSuccessful = new MutableLiveData<>();
        mRegisterMsg=new MutableLiveData<>();
    }

    private boolean validateInputs()
    {
        boolean isValid = true;
        if (firstName.getValue() == null)
        {
            System.out.println("First Name no "+firstName.getValue());
            mRegisterMsg.setValue("First Name cannot be empty");
            isValid = false;

        }


        if (lastName.getValue() == null ) {
            mRegisterMsg.setValue("Last Name cannot be empty");
            isValid = false;

        }

        if (company.getValue() == null )
        {
            mRegisterMsg.setValue("Company cannot be empty");
            isValid = false;

        }

        if (mobileNo.getValue() == null )
        {
            System.out.println("Mobile no "+mobileNo.getValue()+" Password "+password.getValue());
            mRegisterMsg.setValue("Mobile no cannot be empty!");
            isValid = false;
        }

        if (mobileNo.getValue() != null ) {
            if (mobileNo.getValue().length() != 10) {
                System.out.println("Mobile no " + mobileNo.getValue() + " Password " + password.getValue());
                mRegisterMsg.setValue("Please enter 10 digits mobile no");
                isValid = false;

            }
        }

        
        if (password.getValue() == null ) {
            mRegisterMsg.setValue("Password cannot be empty");
            isValid = false;

        }
        if (password.getValue() != null ) {
            if (password.getValue().length() < 6) {
                mRegisterMsg.setValue("Please enter 6 digits password");
                isValid = false;

            }
        }

        if (address.getValue() == null) {
            mRegisterMsg.setValue("Address cannot be empty");
            isValid = false;

        }

        return isValid;
    }


    public void onRegisterCustomer() {
        if(validateInputs())
        {
            customerRepo.registerCustomer(firstName.getValue(),lastName.getValue(),mobileNo.getValue(), password.getValue(),company.getValue(),address.getValue(), mIsRegisterSuccessful,mRegisterMsg);
        } else
        {
            mIsRegisterSuccessful.postValue(false);
        }

    }

    public LiveData<Boolean> getIsSuccessful() {
        return mIsRegisterSuccessful;
    }

    public LiveData<String> getRegisterMsg() {
        return mRegisterMsg;
    }

}
