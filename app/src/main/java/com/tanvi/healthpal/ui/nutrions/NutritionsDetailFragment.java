package com.tanvi.healthpal.ui.nutrions;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.tanvi.healthpal.R;
import com.tanvi.healthpal.databinding.FragmentNutritionsDetailBinding;
import com.tanvi.healthpal.ui.scanqr.ScanQRFragment;
import com.tanvi.healthpal.ui.scanqr.apis.entity.QRScannerViewModel;
import com.tanvi.healthpal.ui.scanqr.apis.entity.elements.Nutrients;

public class NutritionsDetailFragment extends Fragment {
    private FragmentNutritionsDetailBinding binding;
    private String productName;
    private String productImage;
    private String firestoreId;
    private Nutrients nutrients;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentNutritionsDetailBinding.inflate(inflater);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.tvViewDetails.setTag("0");
        binding.ivFav.setTag("0");
        binding.llRow2.setVisibility(View.VISIBLE);
        binding.llRow3.setVisibility(View.VISIBLE);
        binding.llRow4.setVisibility(View.VISIBLE);
        binding.llRow5.setVisibility(View.VISIBLE);

        if(getArguments()!=null){
            nutrients=getArguments().getParcelable(ScanQRFragment.NUTRIMENTS_INFO);
            Log.e("nutrient ","nhi chall reya");
            //Log.e("read",nutrients.getCalcium_unit().toString());

        }
        if(nutrients!=null){
            binding.tvCarbs.setText(nutrients.getCarbohydrates_100g() + nutrients.getCarbohydrates_unit());
            binding.tvFat.setText(nutrients.getFat_100g() + isNOrE(nutrients.getFat_unit()));
            binding.tvProtein.setText(nutrients.getProteins_100g() + isNOrE(nutrients.getProteins_unit()));
            binding.tvSugar.setText(nutrients.getSugars_100g() + isNOrE(nutrients.getSugars_unit()));
            binding.tvCholesterol.setText(nutrients.getCholesterol_100g() + isNOrE(nutrients.getCholesterol_unit()));
            binding.tvDietaryFiber.setText(nutrients.getFiber_100g() + isNOrE(nutrients.getFiber_unit()));
            binding.tvSatFat.setText(nutrients.getSaturated_fat_100g() + isNOrE(nutrients.getSaturated_fat_unit()));
            binding.tvTransFat.setText(nutrients.getTrans_fat_100g() + isNOrE(nutrients.getTrans_fat_unit()));
            binding.tvSodium.setText(nutrients.getSodium_100g() + isNOrE(nutrients.getSodium_unit()));
            binding.tvVitA.setText(nutrients.getVitamin_a_100g() + isNOrE(nutrients.getVitamin_a_unit()));
            binding.tvVitaminC.setText(nutrients.getVitamin_c_100g() + isNOrE(nutrients.getVitamin_c_unit()));
            binding.tvSalt.setText(nutrients.getSalt_100g() + isNOrE(nutrients.getSalt_unit()));
            binding.tvCalcium.setText(nutrients.getCalcium_100g() + isNOrE(nutrients.getCalcium_unit()));
           // binding.tvIron.setText(nutrients.getIron_100g() + isNOrE(nutrients.getIron_unit()));
            binding.tvProdCal.setText(String.valueOf(nutrients.getEnergy_kcal_100g()));
            productName = nutrients.getProduct_name();
          //  productImage = nutrients.getProduct_image();


        }

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