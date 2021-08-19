package com.tanvi.healthpal.ui.profile;

import android.app.Application;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.tanvi.healthpal.utils.SingleLiveEvent;
import com.tanvi.healthpal.R;
import com.tanvi.healthpal.ui.authentication.SignUpModel;
import com.tanvi.healthpal.utils.ValidationUtils;

import kotlin.Pair;

public class UserProfileViewModel extends AndroidViewModel {

    public MutableLiveData<String> emailId = new MutableLiveData<String>("");
    public MutableLiveData<String> errorEmailId = new MutableLiveData<String>("");
    public MutableLiveData<String> fullName = new MutableLiveData<String>("");
    public MutableLiveData<String> errorFullName = new MutableLiveData<String>("");
    public MutableLiveData<String> mobileNumber = new MutableLiveData<String>("");
    public MutableLiveData<String> errorMobileNumber = new MutableLiveData<String>("");
    public MutableLiveData<String> address = new MutableLiveData<String>("");
    public MutableLiveData<String> errorAddress = new MutableLiveData<String>("");
    public MutableLiveData<Boolean> isEnabled = new MutableLiveData<Boolean>(false);
    public SingleLiveEvent<SignUpModel> navigate = new SingleLiveEvent<SignUpModel>();
    public boolean isAllFieldsValid = true;
    public SignUpModel signUpModel = null;

    public UserProfileViewModel(@NonNull Application application) {
        super(application);
    }

    public void setData(SignUpModel signUpModel){
        emailId.postValue(signUpModel.getEmailId());
        fullName.postValue(signUpModel.getFullName());
        mobileNumber.postValue(signUpModel.getPhoneNumber().substring(2));
        address.postValue(signUpModel.getAddress());
    }

    public void onClick(View view){
        if(view.getId() == R.id.btnSave){
            isAllFieldsValid = true;
            isMobileNumberValid();
            isEmailValid();
            isFullNameValid();
            if(isAllFieldsValid){
                isEnabled.postValue(false);
                signUpModel = new SignUpModel(
                        fullName.getValue(), address.getValue(), "", emailId.getValue(), "+1" + mobileNumber.getValue(), ""
                );
                navigate.postValue(signUpModel);
            }
        } else if(view.getId() == R.id.ivEdit){
            if(isEnabled.getValue()){
                isEnabled.postValue(false);
            } else {
                isEnabled.postValue(true);
            }
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

}
