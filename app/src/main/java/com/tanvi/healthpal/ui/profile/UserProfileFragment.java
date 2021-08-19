package com.tanvi.healthpal.ui.profile;

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
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.tanvi.healthpal.BR;
import com.tanvi.healthpal.databinding.FragmentUserProfileBinding;
import com.tanvi.healthpal.preferences.SharedPrefUtils;
import com.tanvi.healthpal.ui.authentication.LoginViewModelFactory;
import com.tanvi.healthpal.ui.authentication.SignUpModel;
import com.tanvi.healthpal.utils.AppProgressUtil;
import com.tanvi.healthpal.utils.Constants;
import com.tanvi.healthpal.utils.ToastUtils;

import org.jetbrains.annotations.NotNull;

public class UserProfileFragment extends Fragment {

    private FragmentUserProfileBinding binding;
    private UserProfileViewModel viewModel;
    private String firebaseId = "";
    private FirebaseFirestore db = null;
    private CollectionReference collectionRef = null;
    private SignUpModel signUpModel = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentUserProfileBinding.inflate(inflater);
        binding.setLifecycleOwner(this);
        viewModel = new ViewModelProvider(this, new LoginViewModelFactory(getActivity().getApplication())).get(UserProfileViewModel.class);
        binding.setVariable(BR.viewModel, viewModel);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        firebaseId = SharedPrefUtils.getStringData(requireContext(), Constants.FIREBASE_ID);

        AppProgressUtil.INSTANCE.showOldProgressDialog(requireContext());
        db = FirebaseFirestore.getInstance();
        collectionRef = db.collection(Constants.USERS);
        collectionRef
                .whereEqualTo("firebaseId", firebaseId)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {
                        AppProgressUtil.INSTANCE.closeOldProgressDialog();
                        if(task.isSuccessful()){
                            if(task.getResult().size() >= 1){
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    signUpModel = new SignUpModel();
                                    signUpModel = document.toObject(SignUpModel.class);
                                }
                                viewModel.setData(signUpModel);
                            }
                        } else {
                            ToastUtils.longCustomToast(getLayoutInflater(), requireView(), 0, "Could not fetch data. Probable reason: " + task.getException());
                        }
                    }
                });

        viewModel.navigate.observe(this, new Observer<SignUpModel>() {
            @Override
            public void onChanged(SignUpModel signUpModel) {
                if(signUpModel != null){
                    updateData(signUpModel);
                }
            }
        });

    }

    private void updateData(SignUpModel signUpModel){
        AppProgressUtil.INSTANCE.showOldProgressDialog(requireContext());
        db.collection(Constants.USERS)
                .document(SharedPrefUtils.getStringData(requireContext(), Constants.FIREBASE_ID))
                .update("fullName", signUpModel.getFullName(), "address", signUpModel.getAddress(), "emailId", signUpModel.getEmailId(), "phoneNumber", signUpModel.getPhoneNumber())
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<Void> task) {
                        AppProgressUtil.INSTANCE.closeOldProgressDialog();
                        if(task.isSuccessful()){
                            ToastUtils.longCustomToast(getLayoutInflater(), requireView(), 0, "Data saved successfully.");
                        } else {
                            ToastUtils.longCustomToast(getLayoutInflater(), requireView(), 0, "Could not save data. Probable reason: " + task.getException());
                        }
                    }
                });
    }

}
