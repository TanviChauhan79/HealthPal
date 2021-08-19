package com.tanvi.healthpal.ui.nutritions;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.tanvi.healthpal.R;
import com.tanvi.healthpal.databinding.FragmentNutritionDetailsBinding;
import com.tanvi.healthpal.preferences.SharedPrefUtils;
import com.tanvi.healthpal.ui.qrscanner.model.Nutriments;
import com.tanvi.healthpal.utils.AppProgressUtil;
import com.tanvi.healthpal.utils.Constants;
import com.tanvi.healthpal.utils.ToastUtils;

import org.jetbrains.annotations.NotNull;

public class NutritionsDetailFragment extends Fragment {

    private FragmentNutritionDetailsBinding binding = null;
    private String productName = null;
    private String productImage = null;
    private String firestoreId = null;
    private Nutriments nutriments = null;
    private FirebaseFirestore db = null;
    private DocumentReference docRef = null;
    private Boolean fromWishList = false;
    private String firebaseId = null;

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        binding = FragmentNutritionDetailsBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        firebaseId = SharedPrefUtils.getStringData(requireContext(), Constants.FIREBASE_ID);

        db = FirebaseFirestore.getInstance();

        binding.tvViewDetails.setTag("0");
        binding.ivFav.setTag("0");
        binding.llRow2.setVisibility(View.GONE);
        binding.llRow3.setVisibility(View.GONE);
        binding.llRow4.setVisibility(View.GONE);
        binding.llRow5.setVisibility(View.GONE);

        if (getArguments() != null) {
//            productImage = getArguments().getString(Constants.PRODUCT_IMAGE);
//            productName = getArguments().getString(Constants.PRODUCT_NAME);
            nutriments = getArguments().getParcelable(Constants.NUTRIMENTS_INFO);
            fromWishList = getArguments().getBoolean(Constants.FROM_WISHLIST);

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

        if(fromWishList){
            firestoreId = nutriments.getId();
            binding.ivFav.setTag("1");
            binding.ivFav.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_favorite));
        }

        binding.ivFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppProgressUtil.INSTANCE.showOldProgressDialog(requireContext());
                switch (String.valueOf(binding.ivFav.getTag())) {
                    case "0": {
                        binding.ivFav.setTag("1");
                        binding.ivFav.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_favorite));
                        // Add
                        nutriments.setUserId(firebaseId);
                        db.collection(Constants.WISHLIST)
                            .add(nutriments)
                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    AppProgressUtil.INSTANCE.closeOldProgressDialog();
                                    firestoreId = documentReference.getId();
                                    ToastUtils.longCustomToast(getLayoutInflater(), requireView(), 0, "Successfully added in wishlist.");
//                                    Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    AppProgressUtil.INSTANCE.closeOldProgressDialog();
                                    binding.ivFav.setTag("0");
                                    binding.ivFav.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_favorite_border));
                                    ToastUtils.longCustomToast(getLayoutInflater(), requireView(), 0, e.getLocalizedMessage());
                                }
                            });
                        break;
                    }
                    case "1": {
                        // Remove
                        docRef = db.collection(Constants.WISHLIST).document(firestoreId);
                        docRef.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                AppProgressUtil.INSTANCE.closeOldProgressDialog();
                                binding.ivFav.setTag("0");
                                binding.ivFav.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_favorite_border));
                                ToastUtils.INSTANCE.longCustomToast(getLayoutInflater(), requireView(), 0, "Successfully removed from wishlist.");
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull @NotNull Exception e) {
                                AppProgressUtil.INSTANCE.closeOldProgressDialog();
                                binding.ivFav.setTag("1");
                                binding.ivFav.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_favorite));
                                ToastUtils.INSTANCE.longCustomToast(getLayoutInflater(), requireView(), 0, e.getLocalizedMessage());
                            }
                        });
                        break;
                    }
                    default: {
                        AppProgressUtil.INSTANCE.closeOldProgressDialog();
                        binding.ivFav.setTag("0");
                        binding.ivFav.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_favorite_border));
                        break;
                    }
                }
            }
        });

        binding.tvViewDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("ddd", String.valueOf(binding.tvViewDetails.getTag()));
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
