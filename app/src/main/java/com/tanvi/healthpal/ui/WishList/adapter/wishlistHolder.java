package com.tanvi.healthpal.ui.WishList.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.checkbox.MaterialCheckBox;
import com.tanvi.healthpal.R;


public class wishlistHolder extends RecyclerView.ViewHolder {

    TextView prodName;
    ImageView prodImage;
    TextView prodCal;
    TextView viewDetails;
    ImageView delete;
    ProgressBar pb;
    MaterialCheckBox mcbCompare;

    public wishlistHolder(@NonNull View itemView) {
        super(itemView);
        prodName = (TextView) itemView.findViewById(R.id.tvProdName);
        prodImage = (ImageView) itemView.findViewById(R.id.ivFoodPlaceholder);
        prodCal = (TextView) itemView.findViewById(R.id.tvProdCal);
        viewDetails = (TextView) itemView.findViewById(R.id.tvViewDetails);
        delete = (ImageView) itemView.findViewById(R.id.ivDelete);
        pb = (ProgressBar) itemView.findViewById(R.id.pbFoodImage);
        mcbCompare = (MaterialCheckBox) itemView.findViewById(R.id.mcbCompare);
    }
}
