package com.tanvi.healthpal.ui.WishList.adapter;

import androidx.annotation.NonNull;

import com.tanvi.healthpal.ui.scanqr.apis.entity.elements.Nutrients;

import org.jetbrains.annotations.NotNull;

public interface IWishListProdDetails {

    void goToDetails(@NotNull Nutrients nutrients);

    void remove(@NonNull Nutrients nutrients,int pos);

    void setSelected(int total,int pos, boolean isRemoved);
}
