package com.tanvi.healthpal.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.tanvi.healthpal.R;
import com.tanvi.healthpal.databinding.FragmentHomeBinding;
import com.tanvi.healthpal.preferences.SharedPrefUtils;
import com.tanvi.healthpal.ui.authentication.AuthenticationActivity;
import com.tanvi.healthpal.utils.AppProgressUtil;
import com.tanvi.healthpal.utils.Constants;

import org.jetbrains.annotations.NotNull;

public class HomeScreenFragment extends Fragment {

    private FragmentHomeBinding binding;

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.tvScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(binding.getRoot()).navigate(R.id.action_homeScreenFragment_to_QRScannerFragment);
            }
        });

        binding.tvWishList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(binding.getRoot()).navigate(R.id.action_homeScreenFragment_to_wishListFragment);
            }
        });

        binding.tvUserProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(binding.getRoot()).navigate(R.id.action_homeScreenFragment_to_userProfileFragment);
            }
        });

        binding.tvChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putBoolean(Constants.FROM_HOME, true);
                Navigation.findNavController(binding.getRoot()).navigate(R.id.action_homeScreenFragment_to_verifyOtpFragment, bundle);
            }
        });

        binding.tvLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppProgressUtil.INSTANCE.showOldProgressDialog(requireContext());
                SharedPrefUtils.saveData(requireContext(), Constants.IS_LOGGED_IN, false);
                SharedPrefUtils.saveData(requireContext(), Constants.FIREBASE_ID, "");
                SharedPrefUtils.getSharedPrefEditor(requireContext(), getString(R.string.app_name)).clear().commit();
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        AppProgressUtil.INSTANCE.closeOldProgressDialog();
                        startActivity(new Intent(requireActivity(), AuthenticationActivity.class));
                        requireActivity().finish();
                    }
                }, 1000);
            }
        });

    }
}
