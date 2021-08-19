package com.tanvi.healthpal.ui.changepassword;

import android.app.Application;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.tanvi.healthpal.utils.SingleLiveEvent;
import com.tanvi.healthpal.utils.ValidationUtils;

import kotlin.Pair;

public class ChangePasswordViewModel extends AndroidViewModel {

    public MutableLiveData<String> password = new MutableLiveData<String>("");
    public MutableLiveData<String> errorPassword = new MutableLiveData<String>("");
    public MutableLiveData<String> confirmPassword = new MutableLiveData<String>("");
    public MutableLiveData<String> errorConfirmPassword = new MutableLiveData<String>("");
    public SingleLiveEvent<String> newPassword = new SingleLiveEvent<>();
    public boolean isAllDataValid = true;

    public ChangePasswordViewModel(@NonNull Application application) {
        super(application);
    }

    public void onClick(View view){
        isAllDataValid = true;
        isPasswordValid();
        isConfirmPasswordValid();
        if(isAllDataValid){
            newPassword.postValue(password.getValue());
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

    private void isConfirmPasswordValid(){
        Pair<Boolean, Integer> result = ValidationUtils.isSameFieldValid(password.getValue(), confirmPassword.getValue());
        if(result.getFirst()){
            errorConfirmPassword.postValue("");
        } else {
            isAllDataValid = false;
            errorConfirmPassword.postValue(String.format(getApplication().getString(result.getSecond())));
        }
    }

}
