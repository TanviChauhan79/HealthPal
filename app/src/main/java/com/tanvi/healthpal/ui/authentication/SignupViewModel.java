package com.tanvi.healthpal.ui.authentication;

import android.app.Application;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.tanvi.healthpal.utils.SingleLiveEvent;
import com.tanvi.healthpal.utils.ValidationUtils;

import org.jetbrains.annotations.NotNull;

import kotlin.Pair;

public class SignupViewModel extends AndroidViewModel {

    public MutableLiveData<String> emailId = new MutableLiveData<String>("");
    public MutableLiveData<String> errorEmailId = new MutableLiveData<String>("");
    public MutableLiveData<String> password = new MutableLiveData<String>("");
    public MutableLiveData<String> errorPassword = new MutableLiveData<String>("");
    public MutableLiveData<String> fullName = new MutableLiveData<String>("");
    public MutableLiveData<String> errorFullName = new MutableLiveData<String>("");
    public MutableLiveData<String> confirmPassword = new MutableLiveData<String>("");
    public MutableLiveData<String> errorConfirmPassword = new MutableLiveData<String>("");
    public MutableLiveData<String> mobileNumber = new MutableLiveData<String>("");
    public MutableLiveData<String> errorMobileNumber = new MutableLiveData<String>("");
    public MutableLiveData<String> address = new MutableLiveData<String>("");
    public MutableLiveData<String> errorAddress = new MutableLiveData<String>("");
    public SingleLiveEvent<SignUpModel> navigate = new SingleLiveEvent<SignUpModel>();
    public boolean isAllFieldsValid = true;
    public SignUpModel signUpModel = null;

    public SignupViewModel(@NonNull @NotNull Application application) {
        super(application);
    }

    public void onClick(View view) {
        isAllFieldsValid = true;
        isMobileNumberValid();
        isEmailValid();
        isPasswordValid();
        isFullNameValid();
        isConfirmPasswordValid();
        if(isAllFieldsValid){
            signUpModel = new SignUpModel(
                    fullName.getValue(), address.getValue(), password.getValue(), emailId.getValue(), "+1" + mobileNumber.getValue(), ""
            );
            navigate.postValue(signUpModel);
        }
    }

    private void isEmailValid(){
        Pair<Boolean, Integer> result = ValidationUtils.isFieldValid(emailId.getValue());
        if(result.getFirst()){
            errorEmailId.postValue("");
        } else {
            isAllFieldsValid = false;
            errorEmailId.postValue(String.format(getApplication().getString(result.getSecond()), "Email Id"));
        }
    }

    private void isMobileNumberValid(){
        Pair<Boolean, Integer> result = ValidationUtils.isMobileNumberValid(mobileNumber.getValue());
        if(result.getFirst()){
            errorMobileNumber.postValue("");
        } else {
            isAllFieldsValid = false;
            errorMobileNumber.postValue(String.format(getApplication().getString(result.getSecond()), "Mobile Number"));
        }
    }

    private void isFullNameValid(){
        Pair<Boolean, Integer> result = ValidationUtils.isFieldValid(mobileNumber.getValue());
        if(result.getFirst()){
            errorFullName.postValue("");
        } else {
            isAllFieldsValid = false;
            errorFullName.postValue(String.format(getApplication().getString(result.getSecond()), "Full Name"));
        }
    }

    private void isPasswordValid(){
        Pair<Boolean, Integer> result = ValidationUtils.isFieldValid(password.getValue());
        if(result.getFirst()){
            errorPassword.postValue("");
        } else {
            isAllFieldsValid = false;
            errorPassword.postValue(String.format(getApplication().getString(result.getSecond()), "Password"));
        }
    }

    private void isConfirmPasswordValid(){
        Pair<Boolean, Integer> result = ValidationUtils.isSameFieldValid(password.getValue(), confirmPassword.getValue());
        if(result.getFirst()){
            errorConfirmPassword.postValue("");
        } else {
            isAllFieldsValid = false;
            errorConfirmPassword.postValue(String.format(getApplication().getString(result.getSecond())));
        }
    }

}
