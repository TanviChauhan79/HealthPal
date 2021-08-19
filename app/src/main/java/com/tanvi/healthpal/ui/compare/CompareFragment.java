package com.tanvi.healthpal.ui.compare;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.tanvi.healthpal.R;
import com.tanvi.healthpal.databinding.FragmentCompareBinding;
import com.tanvi.healthpal.ui.qrscanner.model.Nutriments;
import com.tanvi.healthpal.utils.Constants;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class CompareFragment extends Fragment {

    private FragmentCompareBinding binding = null;
    private ArrayList<Nutriments> items = null;
    private int bestProd0 = 0;
    private int bestProd1 = 0;

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        binding = FragmentCompareBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        items = getArguments().getParcelableArrayList(Constants.NUTRIMENTS_INFO_LIST);

        binding.tvCarbs1.setText(items.get(0).getCarbohydrates_100g() + isNOrE(items.get(0).getCarbohydrates_unit()));
        binding.tvFat1.setText(items.get(0).getFat_100g() + isNOrE(items.get(0).getFat_unit()));
        binding.tvProtein1.setText(items.get(0).getProteins_100g() + isNOrE(items.get(0).getProteins_unit()));
        binding.tvSugars1.setText(items.get(0).getSugars_100g() + isNOrE(items.get(0).getSugars_unit()));
        binding.tvCholesterol1.setText(items.get(0).getCholesterol_100g() + isNOrE(items.get(0).getCholesterol_unit()));
        binding.tvDietaryFiber1.setText(items.get(0).getFiber_100g() + isNOrE(items.get(0).getFiber_unit()));
        binding.tvSaturatedFat1.setText(items.get(0).getSaturated_fat_100g() + isNOrE(items.get(0).getSaturated_fat_unit()));
        binding.tvTransFat1.setText(items.get(0).getTrans_fat_100g() + isNOrE(items.get(0).getTrans_fat_unit()));
        binding.tvSodium1.setText(items.get(0).getSodium_100g() + isNOrE(items.get(0).getSodium_unit()));
        binding.tvVitaminA1.setText(items.get(0).getVitamin_a_100g() + isNOrE(items.get(0).getVitamin_a_unit()));
        binding.tvVitaminC1.setText(items.get(0).getVitamin_c_100g() + isNOrE(items.get(0).getVitamin_c_unit()));
        binding.tvSalt1.setText(items.get(0).getSalt_100g() + isNOrE(items.get(0).getSalt_unit()));
        binding.tvCalcium1.setText(items.get(0).getCalcium_100g() + isNOrE(items.get(0).getCalcium_unit()));
        binding.tvIron1.setText(items.get(0).getIron_100g() + isNOrE(items.get(0).getIron_unit()));
        binding.tvCalories1.setText(String.valueOf(items.get(0).getEnergy_kcal_100g()));

        binding.tvCarbs2.setText(items.get(1).getCarbohydrates_100g() + isNOrE(items.get(1).getCarbohydrates_unit()));
        binding.tvFat2.setText(items.get(1).getFat_100g() + isNOrE(items.get(1).getFat_unit()));
        binding.tvProtein2.setText(items.get(1).getProteins_100g() + isNOrE(items.get(1).getProteins_unit()));
        binding.tvSugars2.setText(items.get(1).getSugars_100g() + isNOrE(items.get(1).getSugars_unit()));
        binding.tvCholesterol2.setText(items.get(1).getCholesterol_100g() + isNOrE(items.get(1).getCholesterol_unit()));
        binding.tvDietaryFiber2.setText(items.get(1).getFiber_100g() + isNOrE(items.get(1).getFiber_unit()));
        binding.tvSaturatedFat2.setText(items.get(1).getSaturated_fat_100g() + isNOrE(items.get(1).getSaturated_fat_unit()));
        binding.tvTransFat2.setText(items.get(1).getTrans_fat_100g() + isNOrE(items.get(1).getTrans_fat_unit()));
        binding.tvSodium2.setText(items.get(1).getSodium_100g() + isNOrE(items.get(1).getSodium_unit()));
        binding.tvVitaminA2.setText(items.get(1).getVitamin_a_100g() + isNOrE(items.get(1).getVitamin_a_unit()));
        binding.tvVitaminC2.setText(items.get(1).getVitamin_c_100g() + isNOrE(items.get(1).getVitamin_c_unit()));
        binding.tvSalt2.setText(items.get(1).getSalt_100g() + isNOrE(items.get(1).getSalt_unit()));
        binding.tvCalcium2.setText(items.get(1).getCalcium_100g() + isNOrE(items.get(1).getCalcium_unit()));
        binding.tvIron2.setText(items.get(1).getIron_100g() + isNOrE(items.get(1).getIron_unit()));
        binding.tvCalories2.setText(String.valueOf(items.get(1).getEnergy_kcal_100g()));

        if(items.get(1).getCarbohydrates_100g() != null && items.get(0).getCarbohydrates_100g() != null){
            if(items.get(1).getCarbohydrates_100g() > items.get(0).getCarbohydrates_100g()){
                binding.tvCarbs2.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.bg_rounded));
                binding.tvCarbs2.setTextColor(ContextCompat.getColor(requireContext(), R.color.white));
            } else {
                binding.tvCarbs1.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.bg_rounded));
                binding.tvCarbs1.setTextColor(ContextCompat.getColor(requireContext(), R.color.white));
            }
        } else {

        }

        if(items.get(1).getProteins_100g() != null && items.get(0).getProteins_100g() != null){
            if(items.get(1).getProteins_100g() > items.get(0).getProteins_100g()){
                binding.tvProtein2.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.bg_rounded));
                binding.tvProtein2.setTextColor(ContextCompat.getColor(requireContext(), R.color.white));
            } else {
                binding.tvProtein1.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.bg_rounded));
                binding.tvProtein1.setTextColor(ContextCompat.getColor(requireContext(), R.color.white));
            }
        } else {

        }

        if(items.get(1).getEnergy_kcal_100g() != null && items.get(0).getEnergy_kcal_100g() != null){
            if(items.get(1).getEnergy_kcal_100g() > items.get(0).getEnergy_kcal_100g()){
                binding.tvCalories2.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.bg_rounded));
                binding.tvCalories2.setTextColor(ContextCompat.getColor(requireContext(), R.color.white));
            } else {
                binding.tvCalories1.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.bg_rounded));
                binding.tvCalories1.setTextColor(ContextCompat.getColor(requireContext(), R.color.white));
            }
        } else {

        }

        if(items.get(1).getFat_100g() != null && items.get(0).getFat_100g() != null){
            if(items.get(1).getFat_100g() > items.get(0).getFat_100g()){
                binding.tvFat2.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.bg_rounded));
                binding.tvFat2.setTextColor(ContextCompat.getColor(requireContext(), R.color.white));
            } else {
                binding.tvFat1.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.bg_rounded));
                binding.tvFat1.setTextColor(ContextCompat.getColor(requireContext(), R.color.white));
            }
        } else {

        }

        binding.tvTitle1.setText(items.get(0).getProduct_name());
        binding.tvTitle2.setText(items.get(1).getProduct_name());

        binding.tvCompareResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(items.get(0).getProteins_100g() > items.get(1).getProteins_100g()){
                    bestProd0++;
                    bestProd1--;
                } else {
                    bestProd1++;
                    bestProd0--;
                }
                if(items.get(0).getFat_100g() < items.get(1).getFat_100g()){
                    bestProd0--;
                    bestProd1++;
                } else {
                    bestProd0++;
                    bestProd1--;
                }
                if(items.get(0).getEnergy_kcal_100g() > items.get(1).getEnergy_kcal_100g()){
                    bestProd0++;
                    bestProd1--;
                } else {
                    bestProd0--;
                    bestProd1++;
                }
                Bundle bundle = new Bundle();
                if(bestProd0 > bestProd1){
                    bundle.putParcelable(Constants.NUTRIMENTS_INFO, items.get(0));
                } else if(bestProd0 < bestProd1){
                    bundle.putParcelable(Constants.NUTRIMENTS_INFO, items.get(1));
                } else {
                    if(items.get(0).getProteins_100g() > items.get(1).getProteins_100g()){
                        bundle.putParcelable(Constants.NUTRIMENTS_INFO, items.get(0));
                    } else {
                        bundle.putParcelable(Constants.NUTRIMENTS_INFO, items.get(1));
                    }
                }
                Navigation.findNavController(binding.getRoot()).navigate(R.id.action_compareFragment_to_resultFragment, bundle);
            }
        });

    }

    private boolean isNullOrEmpty(String str){
        if(str != null && !str.isEmpty()){
            return false;
        }
        return true;
    }

    private String isNOrE(String str) {
        if(!isNullOrEmpty(str)){
            return str;
        } else {
            return "";
        }
    }

}
