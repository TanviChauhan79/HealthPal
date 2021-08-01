package com.tanvi.healthpal.ui.nutrions;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tanvi.healthpal.R;
import com.tanvi.healthpal.databinding.FragmentNutritionsDetailBinding;

public class NutritionsDetailFragment extends Fragment {
    private FragmentNutritionsDetailBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentNutritionsDetailBinding.inflate(inflater);

        return binding.getRoot();
    }
}