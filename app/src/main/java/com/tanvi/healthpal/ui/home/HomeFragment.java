package com.tanvi.healthpal.ui.home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tanvi.healthpal.R;
import com.tanvi.healthpal.databinding.FragmentHomeBinding;


public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentHomeBinding.inflate(inflater);
        return binding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        View.OnClickListener scanner= Navigation.createNavigateOnClickListener(R.id.action_homeFragment_to_scanQRFragment);
        View.OnClickListener wishlist=Navigation.createNavigateOnClickListener(R.id.action_homeFragment_to_wishListFragment);

        binding.tvScan.setOnClickListener(scanner);
        binding.tvWishList.setOnClickListener(wishlist);
    }
}