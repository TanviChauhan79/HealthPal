package com.tanvi.healthpal.ui.wishlist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

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
import com.tanvi.healthpal.preferences.SharedPrefUtils;
import com.tanvi.healthpal.ui.qrscanner.model.Nutriments;
import com.tanvi.healthpal.ui.wishlist.adapter.WishListAdapter;
import com.tanvi.healthpal.ui.wishlist.interfaces.IWishListProdDetails;
import com.tanvi.healthpal.utils.AppProgressUtil;
import com.tanvi.healthpal.utils.Constants;
import com.tanvi.healthpal.utils.ToastUtils;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Map;

public class WishListFragment extends Fragment implements IWishListProdDetails {

    private FragmentWishListBinding binding = null;
    private WishListAdapter wishListAdapter = null;
    private ArrayList<Nutriments> items = new ArrayList<>();
    private FirebaseFirestore db = null;
    private Object object = null;
    private Map<String, Object> map = null;
    private Nutriments nutriments = null;
    private ArrayList<Integer> selectedProds = null;
    private DocumentReference docRef = null;
    private int totalSelected = 0;
    private ArrayList<Nutriments> list = null;
    private String firebaseId = null;

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
        firebaseId = SharedPrefUtils.getStringData(requireContext(), Constants.FIREBASE_ID);
        AppProgressUtil.INSTANCE.showOldProgressDialog(requireContext());
        db.collection(Constants.WISHLIST)
            .whereEqualTo("userId", firebaseId)
            .get()
            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {
                    AppProgressUtil.INSTANCE.closeOldProgressDialog();
                    items.clear();
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            nutriments = new Nutriments();
                            nutriments = document.toObject(Nutriments.class);
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
                        ToastUtils.INSTANCE.longCustomToast(getLayoutInflater(), requireView(), 0, "Could not fetch data. Probable reason: " + task.getException());
                    }
                }
            });

        wishListAdapter = new WishListAdapter(this);

        binding.rvWishList.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false));
        binding.rvWishList.setAdapter(wishListAdapter);

        binding.tvCompare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selectedProds.size() == 2){
                    list.clear();
                    list.add(items.get(selectedProds.get(0)));
                    list.add(items.get(selectedProds.get(1)));
                    Bundle bundle = new Bundle();
                    bundle.putParcelableArrayList(Constants.NUTRIMENTS_INFO_LIST, list);
                    bundle.putBoolean(Constants.FROM_WISHLIST, true);
                    Navigation.findNavController(requireView())
                            .navigate(R.id.action_wishListFragment_to_compareFragment, bundle);
                } else {
                    ToastUtils.INSTANCE.longCustomToast(getLayoutInflater(), requireView(), 0,
                            "Please select 2 products to compare.");
                }
            }
        });

    }

    @Override
    public void goToDetails(@NotNull Nutriments nutriments) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(Constants.NUTRIMENTS_INFO, nutriments);
        bundle.putBoolean(Constants.FROM_WISHLIST, true);
        Navigation.findNavController(getView())
                .navigate(R.id.action_wishListFragment_to_nutritionDetailsFragment, bundle);
    }

    @Override
    public void remove(@NotNull Nutriments nutriments, int pos) {
        AppProgressUtil.INSTANCE.showOldProgressDialog(requireContext());
        docRef = db.collection(Constants.WISHLIST).document(nutriments.getId());
        docRef.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                AppProgressUtil.INSTANCE.closeOldProgressDialog();
                wishListAdapter.remove(pos);
                ToastUtils.INSTANCE.longCustomToast(getLayoutInflater(), requireView(), 0, "Successfully removed from wishlist.");
            }
        })
        .addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull @NotNull Exception e) {
                AppProgressUtil.INSTANCE.closeOldProgressDialog();
                ToastUtils.INSTANCE.longCustomToast(getLayoutInflater(), requireView(), 0, e.getLocalizedMessage());
            }
        });
    }

    @Override
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
