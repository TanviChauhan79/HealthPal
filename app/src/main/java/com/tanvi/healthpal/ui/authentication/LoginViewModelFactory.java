package com.tanvi.healthpal.ui.authentication;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.tanvi.healthpal.ui.changepassword.ChangePasswordViewModel;
import com.tanvi.healthpal.ui.profile.UserProfileViewModel;

import org.jetbrains.annotations.NotNull;

public class LoginViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private Application application;

    public LoginViewModelFactory(Application application) {
        this.application = application;
    }

    @NonNull
    @NotNull
    @Override
    public <T extends ViewModel> T create(@NonNull @NotNull Class<T> modelClass) {
        if (modelClass == LoginViewModel.class) {
            try {
                return (T) new LoginViewModel(application);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (modelClass == SignupViewModel.class) {
            try {
                return (T) new SignupViewModel(application);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (modelClass == ChangePasswordViewModel.class) {
            try {
                return (T) new ChangePasswordViewModel(application);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (modelClass == UserProfileViewModel.class) {
            try {
                return (T) new UserProfileViewModel(application);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
