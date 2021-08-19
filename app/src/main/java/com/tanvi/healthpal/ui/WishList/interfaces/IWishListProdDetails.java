package com.tanvi.healthpal.ui.wishlist.interfaces;

import com.tanvi.healthpal.ui.qrscanner.model.Nutriments;

public interface IWishListProdDetails {

    void goToDetails(Nutriments nutriments);
    void remove(Nutriments nutriments, int pos);
    void setSelected(int total, int pos, boolean isRemove);

}
