package com.tanvi.healthpal.ui.changepassword;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.tanvi.healthpal.BR;
import com.tanvi.healthpal.MainActivity;
import com.tanvi.healthpal.databinding.FragmentChangePasswordBinding;
import com.tanvi.healthpal.preferences.SharedPrefUtils;
import com.tanvi.healthpal.ui.authentication.AuthenticationActivity;
import com.tanvi.healthpal.ui.authentication.LoginViewModelFactory;
import com.tanvi.healthpal.ui.authentication.SignUpModel;
import com.tanvi.healthpal.utils.AppProgressUtil;
import com.tanvi.healthpal.utils.Constants;
import com.tanvi.healthpal.utils.ToastUtils;

public class ChangePasswordFragment extends Fragment {

    private FragmentChangePasswordBinding binding;
    private ChangePasswordViewModel viewModel;
    private SignUpModel signUpModel;
    private CollectionReference collectionRef = null;
    private FirebaseFirestore db;
    private Boolean fromHome = false;
    private String firebaseUserId = "";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentChangePasswordBinding.inflate(inflater);
        binding.setLifecycleOwner(this);
        viewModel = new ViewModelProvider(this, new LoginViewModelFactory(getActivity().getApplication())).get(ChangePasswordViewModel.class);
        binding.setVariable(BR.viewModel, viewModel);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        signUpModel = getArguments().getParcelable(Constants.SIGN_UP_MODEL);

        if(signUpModel != null){
            firebaseUserId = signUpModel.getFirebaseId();
        }

        fromHome = getArguments().getBoolean(Constants.FROM_HOME);

        if(fromHome){
            firebaseUserId = SharedPrefUtils.getStringData(requireContext(),Constants.FIREBASE_ID);
        }

        db = FirebaseFirestore.getInstance();

        viewModel.newPassword.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if(s != null && !s.isEmpty()){
                    setNewPassword(s, firebaseUserId);
                }
            }
        });

    }

    private void setNewPassword(String pwd, String firebaseId){
        AppProgressUtil.INSTANCE.showOldProgressDialog(requireContext());
        collectionRef = db.collection(Constants.USERS);
        collectionRef
            .document(firebaseId)
            .update("password", pwd)
            .addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    AppProgressUtil.INSTANCE.closeOldProgressDialog();
                    if(task.isSuccessful()){
                        ToastUtils.longCustomToast(getLayoutInflater(), requireView(), 0, "Password changed in successfully");
                        SharedPrefUtils.saveData(requireContext(), Constants.IS_LOGGED_IN, true);
                        SharedPrefUtils.saveData(requireContext(), Constants.FIREBASE_ID, signUpModel.getFirebaseId());
                        startActivity(new Intent(requireContext(), MainActivity.class));
                        if(requireActivity() instanceof AuthenticationActivity){
                            requireActivity().finish();
                        }
                    } else {
                        ToastUtils.longCustomToast(getLayoutInflater(), requireView(), 0, "Could not log in. Probable reason: " + task.getException());
                    }
                }
            });
    }

}
