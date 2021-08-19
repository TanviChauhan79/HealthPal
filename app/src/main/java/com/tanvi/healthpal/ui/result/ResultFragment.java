package com.tanvi.healthpal.ui.result;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.tanvi.healthpal.R;
import com.tanvi.healthpal.databinding.FragmentNutritionDetailsBinding;
import com.tanvi.healthpal.databinding.FragmentResultBinding;
import com.tanvi.healthpal.ui.qrscanner.model.Nutriments;
import com.tanvi.healthpal.utils.Constants;

import org.jetbrains.annotations.NotNull;

public class ResultFragment extends Fragment {

    private FragmentResultBinding binding;
    private String productName = null;
    private String productImage = null;
    private Nutriments nutriments = null;

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        binding = FragmentResultBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.tvViewDetails.setTag("0");
        binding.llRow2.setVisibility(View.GONE);
        binding.llRow3.setVisibility(View.GONE);
        binding.llRow4.setVisibility(View.GONE);
        binding.llRow5.setVisibility(View.GONE);

        if (getArguments() != null) {
            nutriments = getArguments().getParcelable(Constants.NUTRIMENTS_INFO);

            if(nutriments != null){
                binding.tvCarbs.setText(nutriments.getCarbohydrates_100g() + isNOrE(nutriments.getCarbohydrates_unit()));
                binding.tvFat.setText(nutriments.getFat_100g() + isNOrE(nutriments.getFat_unit()));
                binding.tvProtein.setText(nutriments.getProteins_100g() + isNOrE(nutriments.getProteins_unit()));
                binding.tvSugar.setText(nutriments.getSugars_100g() + isNOrE(nutriments.getSugars_unit()));
                binding.tvCholesterol.setText(nutriments.getCholesterol_100g() + isNOrE(nutriments.getCholesterol_unit()));
                binding.tvDietaryFiber.setText(nutriments.getFiber_100g() + isNOrE(nutriments.getFiber_unit()));
                binding.tvSatFat.setText(nutriments.getSaturated_fat_100g() + isNOrE(nutriments.getSaturated_fat_unit()));
                binding.tvTransFat.setText(nutriments.getTrans_fat_100g() + isNOrE(nutriments.getTrans_fat_unit()));
                binding.tvSodium.setText(nutriments.getSodium_100g() + isNOrE(nutriments.getSodium_unit()));
                binding.tvVitA.setText(nutriments.getVitamin_a_100g() + isNOrE(nutriments.getVitamin_a_unit()));
                binding.tvVitaminC.setText(nutriments.getVitamin_c_100g() + isNOrE(nutriments.getVitamin_c_unit()));
                binding.tvSalt.setText(nutriments.getSalt_100g() + isNOrE(nutriments.getSalt_unit()));
                binding.tvCalcium.setText(nutriments.getCalcium_100g() + isNOrE(nutriments.getCalcium_unit()));
                binding.tvIron.setText(nutriments.getIron_100g() + isNOrE(nutriments.getIron_unit()));
                binding.tvProdCal.setText(String.valueOf(nutriments.getEnergy_kcal_100g()));
                productName = nutriments.getProduct_name();
                productImage = nutriments.getProduct_image();
            }

            if(!isNullOrEmpty(productImage)){
                Picasso.get()
                        .load(productImage)
                        .placeholder(R.drawable.food_placeholder)
                        .error(R.drawable.food_placeholder)
                        .into(binding.ivFoodPlaceholder, new Callback() {
                            @Override
                            public void onSuccess() {
                                binding.pbFoodImage.setVisibility(View.GONE);
                            }

                            @Override
                            public void onError(Exception e) {
                                binding.pbFoodImage.setVisibility(View.GONE);
                            }
                        });
            } else {
                binding.pbFoodImage.setVisibility(View.GONE);
            }
            if(!isNullOrEmpty(productName)){
                binding.tvProdName.setText(productName);
            }

        }

        binding.tvViewDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (String.valueOf(binding.tvViewDetails.getTag())) {
                    case "0" : {
                        binding.llRow2.setVisibility(View.VISIBLE);
                        binding.llRow3.setVisibility(View.VISIBLE);
                        binding.llRow4.setVisibility(View.VISIBLE);
                        binding.llRow5.setVisibility(View.VISIBLE);
                        binding.tvViewDetails.setTag("1");
                        binding.tvViewDetails.setText(getString(R.string.tv_view_less));
                        break;
                    }
                    case "1": {
                        binding.llRow2.setVisibility(View.GONE);
                        binding.llRow3.setVisibility(View.GONE);
                        binding.llRow4.setVisibility(View.GONE);
                        binding.llRow5.setVisibility(View.GONE);
                        binding.tvViewDetails.setTag("0");
                        binding.tvViewDetails.setText(getString(R.string.tv_view_more));
                        break;
                    }
                    default: {
                        binding.tvViewDetails.setTag("0");
                        binding.llRow2.setVisibility(View.GONE);
                        binding.llRow3.setVisibility(View.GONE);
                        binding.llRow4.setVisibility(View.GONE);
                        binding.llRow5.setVisibility(View.GONE);
                        binding.tvViewDetails.setText(getString(R.string.tv_view_more));
                        break;
                    }
                }
            }
        });

        binding.tvShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                String shareBody = "Here is the best product: " + nutriments.getProduct_name()
                        + "\nProtein: " + nutriments.getProteins_100g()
                        + "\nCarbs: " + nutriments.getCarbohydrates_100g()
                        + "\nFat: " + nutriments.getFat_100g();
                /*The type of the content is text, obviously.*/
                intent.setType("text/plain");
                /*Applying information Subject and Body.*/
                intent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                /*Fire!*/
                startActivity(Intent.createChooser(intent, getString(R.string.tv_share_using)));
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
