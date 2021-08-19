package com.tanvi.healthpal.ui.authentication;

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
import androidx.navigation.Navigation;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.tanvi.healthpal.BR;
import com.tanvi.healthpal.MainActivity;
import com.tanvi.healthpal.R;
import com.tanvi.healthpal.databinding.FragmentLoginBinding;
import com.tanvi.healthpal.preferences.SharedPrefUtils;
import com.tanvi.healthpal.utils.AppProgressUtil;
import com.tanvi.healthpal.utils.Constants;
import com.tanvi.healthpal.utils.ToastUtils;

import org.jetbrains.annotations.NotNull;

public class LoginFragment extends Fragment {

    private FragmentLoginBinding binding;
    private LoginViewModel viewModel;
    private DocumentReference docRef = null;
    private CollectionReference collectionRef = null;
    private FirebaseFirestore db = null;
    private SignUpModel signUpModel = null;

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(this, new LoginViewModelFactory(getActivity().getApplication())).get(LoginViewModel.class);
//        viewModel = new ViewModelProvider(this, new ViewModelFactory(getActivity().getApplication())).get(LoginViewModel.class);
        binding.setLifecycleOwner(this);
        binding.setVariable(BR.viewModel, viewModel);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        db = FirebaseFirestore.getInstance();

        binding.tvGoToSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(binding.getRoot()).navigate(R.id.action_loginFragment_to_signupFragment);
            }
        });

        viewModel.forgotPassword.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if(s != null && !s.isEmpty()){
                    checkIfUserExists(s);
                }
            }
        });

        viewModel.dataValid.observe(getViewLifecycleOwner(), new Observer<SignInModel>() {
            @Override
            public void onChanged(SignInModel signInModel) {
                if(signInModel != null){
                    AppProgressUtil.INSTANCE.showOldProgressDialog(requireContext());
                    collectionRef = db.collection(Constants.USERS);
                    collectionRef
                        .whereEqualTo("emailId", signInModel.getEmailId())
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                AppProgressUtil.INSTANCE.closeOldProgressDialog();
                                if(task.isSuccessful()){
                                    if(task.getResult().size() >= 1){
                                        AppProgressUtil.INSTANCE.showOldProgressDialog(requireContext());
                                        collectionRef
                                            .whereEqualTo("password", signInModel.getPassword())
                                            .get()
                                            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                                @Override
                                                public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {
                                                    AppProgressUtil.INSTANCE.closeOldProgressDialog();
                                                    if(task.isSuccessful() && task.getResult().size() >= 1){
                                                        for (QueryDocumentSnapshot document : task.getResult()) {
                                                            signUpModel = new SignUpModel();
                                                            signUpModel = document.toObject(SignUpModel.class);
                                                            signUpModel.setFirebaseId(document.getId());
                                                        }
                                                        SharedPrefUtils.saveData(requireContext(), Constants.IS_LOGGED_IN, true);
                                                        SharedPrefUtils.saveData(requireContext(), Constants.FIREBASE_ID, signUpModel.getFirebaseId());
                                                        ToastUtils.longCustomToast(getLayoutInflater(), requireView(), 0, "Logged in successfully");
                                                        startActivity(new Intent(requireContext(), MainActivity.class));
                                                        requireActivity().finish();
                                                    } else {
                                                        ToastUtils.longCustomToast(getLayoutInflater(), requireView(), 0, "Could not log in. Probable reason: " + task.getException());
                                                    }
                                                }
                                            });
                                    } else {
                                        ToastUtils.longCustomToast(getLayoutInflater(), requireView(), 0, "User doesn't exist." + task.getException());
                                    }
                                } else {
                                    ToastUtils.longCustomToast(getLayoutInflater(), requireView(), 0, "Could not log in. Probable reason: " + task.getException());
                                }
                            }
                        });

                }
            }
        });

    }

    private void checkIfUserExists(String email){
        AppProgressUtil.INSTANCE.showOldProgressDialog(requireContext());
        collectionRef = db.collection(Constants.USERS);
        collectionRef
                .whereEqualTo("emailId", email)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        AppProgressUtil.INSTANCE.closeOldProgressDialog();
                        if(task.isSuccessful()){
                            if(task.getResult().size() >= 1){
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    signUpModel = new SignUpModel();
                                    signUpModel = document.toObject(SignUpModel.class);
                                    signUpModel.setFirebaseId(document.getId());
                                }
                                // redirect
                                Bundle bundle = new Bundle();
                                bundle.putParcelable(Constants.SIGN_UP_MODEL, signUpModel);
                                bundle.putBoolean(Constants.IS_FORGOT_PASSWORD, true);
                                Navigation.findNavController(binding.getRoot()).navigate(R.id.action_loginFragment_to_verifyOtpFragment, bundle);
                            } else {
                                ToastUtils.longCustomToast(getLayoutInflater(), requireView(), 0, "User doesn't exist." + task.getException());
                            }
                        } else {
                            ToastUtils.longCustomToast(getLayoutInflater(), requireView(), 0, "Could not log in. Probable reason: " + task.getException());
                        }
                    }
                });
    }

}
