package com.tanvi.healthpal.ui.WishList;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.tanvi.healthpal.R;
import com.tanvi.healthpal.databinding.FragmentWishListBinding;
import com.tanvi.healthpal.ui.WishList.adapter.IWishListProdDetails;
import com.tanvi.healthpal.ui.WishList.adapter.WishlistAdapter;
import com.tanvi.healthpal.ui.scanqr.ScanQRFragment;
import com.tanvi.healthpal.ui.scanqr.apis.entity.elements.Nutrients;
import com.tanvi.healthpal.ui.scanqr.apis.entity.elements.NutritionalInfoFromApi;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Map;

public class WishListFragment extends Fragment {

    private FragmentWishListBinding binding = null;
    private WishlistAdapter wishListAdapter = null;
    private ArrayList<Nutrients> items = new ArrayList<>();
    private FirebaseFirestore db = null;
    private Object object = null;
    private Map<String, Object> map = null;
    private Nutrients nutriments = null;
    private ArrayList<Integer> selectedProds = null;
    private DocumentReference docRef = null;
    private int totalSelected = 0;
    private ArrayList<Nutrients> list = null;


    public final static String WISHLIST="wishlist";
    public final static String FROM_WISHLIST="from_wishlist";


    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        binding = FragmentWishListBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        selectedProds = new ArrayList<>();
        list = new ArrayList<>();

        db = FirebaseFirestore.getInstance();

        db.collection(WISHLIST)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {
                        items.clear();
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                nutriments = new Nutrients();
                                nutriments = document.toObject(Nutrients.class);
                                nutriments.setId(document.getId());
                                items.add(
                                        nutriments
                                );
                            }
                            wishListAdapter.setItemList(items);
                            if(items.isEmpty()){
                                binding.tvShowNoData.setVisibility(View.VISIBLE);
                                binding.rvWishList.setVisibility(View.GONE);
                            } else {
                                binding.tvShowNoData.setVisibility(View.GONE);
                                binding.rvWishList.setVisibility(View.VISIBLE);
                            }
                        } else {
                            binding.tvShowNoData.setVisibility(View.VISIBLE);
                            binding.rvWishList.setVisibility(View.GONE);
                        }
                    }
                });

        //wishListAdapter= new WishlistAdapter(this);
        binding.rvWishList.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false));
        binding.rvWishList.setAdapter(wishListAdapter);

    }

    public void goToDetails(@NotNull Nutrients nutriments) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(ScanQRFragment.NUTRIMENTS_INFO, (Parcelable) nutriments);
        bundle.putBoolean(FROM_WISHLIST, true);
        Navigation.findNavController(getView())
                .navigate(R.id.action_wishListFragment_to_nutritionsDetailFragment, bundle);
    }

    public void remove(@NotNull Nutrients nutriments, int pos) {
        docRef = db.collection(WISHLIST).document(nutriments.getId());
        docRef.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {

                wishListAdapter.remove(pos);

            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull @NotNull Exception e) {
                    }
                });
    }

    public void setSelected(int total, int pos, boolean isRemove) {
        totalSelected = total;
        if(total > 0){
            binding.tvCompare.setVisibility(View.VISIBLE);
        } else {
            binding.tvCompare.setVisibility(View.GONE);
        }
        if(isRemove){
            selectedProds.remove((Object) pos);
        } else {
            selectedProds.add(pos);
        }
        binding.tvCompare.setText(String.format(getString(R.string.tv_compare), String.valueOf(total)));
    }
}
