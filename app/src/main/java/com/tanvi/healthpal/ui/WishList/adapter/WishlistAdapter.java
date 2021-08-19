package com.tanvi.healthpal.ui.wishlist.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.checkbox.MaterialCheckBox;
import com.makeramen.roundedimageview.RoundedImageView;
import com.tanvi.healthpal.R;
import com.tanvi.healthpal.ui.qrscanner.model.Nutriments;
import com.tanvi.healthpal.ui.wishlist.interfaces.IWishListProdDetails;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.tanvi.healthpal.utils.ToastUtils;

public class WishListAdapter extends RecyclerView.Adapter<ViewHolder> {

    private ArrayList<Nutriments> items = new ArrayList<>();
    private ArrayList<Integer> selectedItems = new ArrayList<>();
    private IWishListProdDetails iWishListProdDetails = null;
    private LayoutInflater layoutInflater = null;

    public WishListAdapter(IWishListProdDetails iWishListProdDetails){
        this.iWishListProdDetails = iWishListProdDetails;
    }
    
    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        layoutInflater = LayoutInflater.from(parent.getContext());
        final View view = layoutInflater.inflate(R.layout.item_wish_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(iWishListProdDetails != null){
                    iWishListProdDetails.remove(items.get(holder.getAdapterPosition()), holder.getAdapterPosition());
                }
            }
        });
        holder.viewDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(iWishListProdDetails != null){
                    iWishListProdDetails.goToDetails(items.get(holder.getAdapterPosition()));
                }
            }
        });
        if(!isNullOrEmpty(items.get(holder.getAdapterPosition()).getProduct_image())){
            Picasso.get()
                .load(items.get(holder.getAdapterPosition()).getProduct_image())
                .placeholder(R.drawable.food_placeholder)
                .error(R.drawable.food_placeholder)
                .into(holder.prodImage, new Callback() {
                    @Override
                    public void onSuccess() {
                        holder.pb.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError(Exception e) {
                        holder.pb.setVisibility(View.GONE);
                    }
                });
        } else {
            holder.pb.setVisibility(View.GONE);
        }
        if(!isNullOrEmpty(items.get(holder.getAdapterPosition()).getProduct_name())){
            holder.prodName.setText(items.get(holder.getAdapterPosition()).getProduct_name());
        }
        if(!isNullOrEmpty(String.valueOf(items.get(holder.getAdapterPosition()).getEnergy_kcal_100g()))){
            holder.prodCal.setText(String.valueOf(items.get(holder.getAdapterPosition()).getEnergy_kcal_100g()));
        }
        holder.mcbCompare.setChecked(items.get(holder.getAdapterPosition()).isChecked());
        holder.mcbCompare.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(selectedItems.size() == 2 && isChecked){
                    ToastUtils.INSTANCE.longCustomToast(layoutInflater, holder.mcbCompare.getRootView(), 0,
                            "You can't compare more than 2 products at once.");
                    holder.mcbCompare.setChecked(false);
                    return;
                }
                if(isChecked){
                    selectedItems.add(holder.getAdapterPosition());
                } else {
                    selectedItems.remove((Object) holder.getAdapterPosition());
                }
                iWishListProdDetails.setSelected(selectedItems.size(), holder.getAdapterPosition(), !isChecked);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItemList(ArrayList<Nutriments> itemList){
        this.items = itemList;
        notifyDataSetChanged();
    }

    public void remove(int pos){
        items.remove(pos);
        notifyDataSetChanged();
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

class ViewHolder extends RecyclerView.ViewHolder {

    TextView prodName;
    RoundedImageView prodImage;
    TextView prodCal;
    TextView viewDetails;
    ImageView delete;
    ProgressBar pb;
    MaterialCheckBox mcbCompare;

    public ViewHolder(@NonNull @NotNull View itemView) {
        super(itemView);
        prodName = (TextView) itemView.findViewById(R.id.tvProdName);
        prodImage = (RoundedImageView) itemView.findViewById(R.id.ivFoodPlaceholder);
        prodCal = (TextView) itemView.findViewById(R.id.tvProdCal);
        viewDetails = (TextView) itemView.findViewById(R.id.tvViewDetails);
        delete = (ImageView) itemView.findViewById(R.id.ivDelete);
        pb = (ProgressBar) itemView.findViewById(R.id.pbFoodImage);
        mcbCompare = (MaterialCheckBox) itemView.findViewById(R.id.mcbCompare);
    }

}
