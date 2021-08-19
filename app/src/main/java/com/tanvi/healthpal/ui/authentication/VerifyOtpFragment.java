package com.tanvi.healthpal.ui.authentication;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.mukesh.OnOtpCompletionListener;
import com.tanvi.healthpal.MainActivity;
import com.tanvi.healthpal.R;
import com.tanvi.healthpal.databinding.FragmentVerifyOtpBinding;
import com.tanvi.healthpal.preferences.SharedPrefUtils;
import com.tanvi.healthpal.utils.AppProgressUtil;
import com.tanvi.healthpal.utils.Constants;
import com.tanvi.healthpal.utils.ToastUtils;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;

public class VerifyOtpFragment extends Fragment {

    private FragmentVerifyOtpBinding binding;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks callBacks;
    private SignUpModel signUpModel;
    private FirebaseAuth firebaseAuth;
    private String verificationId;
    private CountDownTimer countDownTimer;
    private PhoneAuthProvider.ForceResendingToken resendToken;

    private FirebaseFirestore db = null;
    private DocumentReference docRef = null;
    private boolean isFromForgotPassword = false;
    private boolean isFromHome = false;

    private CollectionReference collectionRef = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentVerifyOtpBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        db = FirebaseFirestore.getInstance();

        countDownTimer = new CountDownTimer(60000, 1000) {

            public void onTick(long millisUntilFinished) {
                //here you can have your logic to set text to edittext
                if(VerifyOtpFragment.this.isVisible()){
                    binding.tvEnterOtpResend.setText(String.format(getString(R.string.tv_otp_resend_msg), String.valueOf((millisUntilFinished / 1000))));
                    binding.tvEnterOtpResend.setVisibility(View.VISIBLE);
                    binding.tvResend.setVisibility(View.GONE);
                }
            }

            public void onFinish() {
                binding.tvEnterOtpResend.setVisibility(View.GONE);
                binding.tvResend.setVisibility(View.VISIBLE);
            }

        };

        signUpModel = getArguments().getParcelable(Constants.SIGN_UP_MODEL);
        isFromForgotPassword = getArguments().getBoolean(Constants.IS_FORGOT_PASSWORD);
        isFromHome = getArguments().getBoolean(Constants.FROM_HOME);

        binding.otpView.setOtpCompletionListener(new OnOtpCompletionListener() {
            @Override
            public void onOtpCompleted(String otp) {
                verifyCode(otp);
            }
        });

        binding.tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                countDownTimer.cancel();
                requireActivity().onBackPressed();
            }
        });

        binding.tvResend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendVerificationCode();
            }
        });

        AppProgressUtil.INSTANCE.showOldProgressDialog(requireContext());

        callBacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                // below line is used for getting OTP code
                // which is sent in phone auth credentials.
                AppProgressUtil.INSTANCE.closeOldProgressDialog();
                final String code = phoneAuthCredential.getSmsCode();

                // checking if the code
                // is null or not.
                if (code != null) {
                    // if the code is not null then
                    // we are setting that code to
                    // our OTP edittext field.
                    binding.otpView.setText(code);

                    // after setting this code
                    // to OTP edittext field we
                    // are calling our verifycode method.
                    verifyCode(code);
                } else {

                }
//                signInWithPhoneAuthCredential(phoneAuthCredential);
            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {
                AppProgressUtil.INSTANCE.closeOldProgressDialog();
                if (e instanceof FirebaseAuthInvalidCredentialsException) {
                    // Invalid request
                } else if (e instanceof FirebaseTooManyRequestsException) {
                    // The SMS quota for the project has been exceeded
                }
            }

            @Override
            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
//                super.onCodeSent(s, forceResendingToken);
                // The SMS verification code has been sent to the provided phone number, we
                // now need to ask the user to enter the code and then construct a credential
                // by combining the code with a verification ID.
                AppProgressUtil.INSTANCE.closeOldProgressDialog();
                ToastUtils.longCustomToast(getLayoutInflater(), requireView(), 0, "OTP sent");
                countDownTimer.start();
                // Save verification ID and resending token so we can use them later
                verificationId = s;
                resendToken = forceResendingToken;

            }
        };

        firebaseAuth = FirebaseAuth.getInstance();

        if(isFromHome){
            getUserNumber();
        } else {
            sendVerificationCode();
        }

    }

    private void getUserNumber(){
        AppProgressUtil.INSTANCE.showOldProgressDialog(requireContext());
        collectionRef = db.collection(Constants.USERS);
        collectionRef
                .whereEqualTo("firebaseId", SharedPrefUtils.getStringData(requireContext(), Constants.FIREBASE_ID))
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
                                sendVerificationCode();
                            }
                        }
                    }
                });
    }

    // below method is use to verify code from Firebase.
    private void verifyCode(String code) {
        // below line is used for getting getting
        // credentials from our verification id and code.
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);

        // after getting credential we are
        // calling sign in method.
        signInWithPhoneAuthCredential(credential);
    }

    private void sendVerificationCode() {
        // this method is used for getting
        // OTP on user phone number.

        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(firebaseAuth)
                        .setPhoneNumber(signUpModel.getPhoneNumber())       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(requireActivity())                 // Activity (for callback binding)
                        .setCallbacks(callBacks)          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential phoneAuthCredential) {
        firebaseAuth.signInWithCredential(phoneAuthCredential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(VerifyOtpFragment.class.getSimpleName(), "signInWithCredential:success");

                            FirebaseUser user = task.getResult().getUser();
                            if(isFromForgotPassword || isFromHome){
                                // Redirect to change password
                                Bundle bundle = new Bundle();
                                bundle.putParcelable(Constants.SIGN_UP_MODEL, signUpModel);
                                if(isFromHome){
                                    bundle.putBoolean(Constants.FROM_HOME, isFromHome);
                                }
                                Navigation.findNavController(requireView()).navigate(R.id.action_verifyOtpFragment_to_changePasswordFragment2, bundle);
                            } else {
                                saveUser(signUpModel);
                            }
                            // Update UI
                            if(isFromHome){
                                ToastUtils.longCustomToast(getLayoutInflater(), requireView(), 0, "Otp verified successfully");
                            } else {
                                ToastUtils.longCustomToast(getLayoutInflater(), requireView(), 0, "Sign in success");
                            }

                        } else {
                            // Sign in failed, display a message and update the UI
                            Log.w(VerifyOtpFragment.class.getSimpleName(), "signInWithCredential:failure", task.getException());
                            ToastUtils.longCustomToast(getLayoutInflater(), requireView(), 0, "signInWithCredential:failure" + task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                            }
                        }
                    }
                });
    }

    private void saveUser(SignUpModel signUpModel){
        AppProgressUtil.INSTANCE.showOldProgressDialog(requireContext());
        db.collection(Constants.USERS)
            .add(signUpModel)
            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                @Override
                public void onSuccess(DocumentReference documentReference) {
                    db.collection(Constants.USERS)
                        .document(documentReference.getId())
                        .update("firebaseId", documentReference.getId())
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull @NotNull Task<Void> task) {
                                AppProgressUtil.INSTANCE.closeOldProgressDialog();
                                if(task.isSuccessful()){
                                    SharedPrefUtils.saveData(requireContext(), Constants.IS_LOGGED_IN, true);
                                    SharedPrefUtils.saveData(requireContext(), Constants.FIREBASE_ID, documentReference.getId());
                                    ToastUtils.longCustomToast(getLayoutInflater(), requireView(), 0, "Successfully logged in.");
                                    startActivity(new Intent(requireContext(), MainActivity.class));
                                    requireActivity().finish();
                                } else{
                                    ToastUtils.longCustomToast(getLayoutInflater(), requireView(), 0, String.valueOf(task.getException()));
                                }
                            }
                        });
                }
            })
            .addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    AppProgressUtil.INSTANCE.closeOldProgressDialog();
                    ToastUtils.longCustomToast(getLayoutInflater(), requireView(), 0, e.getLocalizedMessage());
                }
            });
    }

}
