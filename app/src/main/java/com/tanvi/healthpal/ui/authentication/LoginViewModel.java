package com.tanvi.healthpal.ui.authentication;

import android.app.Application;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.tanvi.healthpal.utils.SingleLiveEvent;
import com.tanvi.healthpal.R;
import com.tanvi.healthpal.utils.ValidationUtils;

import org.jetbrains.annotations.NotNull;

import kotlin.Pair;

public class LoginViewModel extends AndroidViewModel {

    public MutableLiveData<String> emailId = new MutableLiveData<String>("");
    public MutableLiveData<String> errorEmailId = new MutableLiveData<String>("");
    public MutableLiveData<String> mobileNumber = new MutableLiveData<String>("");
    public MutableLiveData<String> errorMobileNumber = new MutableLiveData<String>("");
    public MutableLiveData<String> password = new MutableLiveData<String>("");
    public MutableLiveData<String> errorPassword = new MutableLiveData<String>("");
    public SingleLiveEvent<SignInModel> dataValid = new SingleLiveEvent<>();
    public SingleLiveEvent<String> forgotPassword = new SingleLiveEvent<>();
    public boolean isAllDataValid = true;
    public SignInModel signInModel;

    public LoginViewModel(@NonNull @NotNull Application application) {
        super(application);
    }

    public void onClick(View view) {
        if(view.getId() == R.id.tvGoToForgotPassword){
            isAllDataValid = true;
            isMobileNumberValid();
            if(isAllDataValid){
                forgotPassword.postValue(emailId.getValue());
            }
        } else if(view.getId() == R.id.btnLogin){
            isAllDataValid = true;
            isMobileNumberValid();
            isPasswordValid();
            if(isAllDataValid){
                signInModel = new SignInModel(password.getValue().toString(), emailId.getValue().toString());
                dataValid.postValue(signInModel);
            }
        }
    }

    private void isMobileNumberValid(){
        Pair<Boolean, Integer> result = ValidationUtils.isFieldValid(emailId.getValue());
        if(result.getFirst()){
            errorEmailId.postValue("");
        } else {
            isAllDataValid = false;
            errorEmailId.postValue(String.format(getApplication().getString(result.getSecond()), "Email Id"));
        }
    }

    private void isPasswordValid(){
        Pair<Boolean, Integer> result = ValidationUtils.isFieldValid(password.getValue());
        if(result.getFirst()){
            errorPassword.postValue("");
        } else {
            isAllDataValid = false;
            errorPassword.postValue(String.format(getApplication().getString(result.getSecond()), "Password"));
        }
    }

}
