package com.tanvi.healthpal.ui.WishList.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.tanvi.healthpal.R;
import com.tanvi.healthpal.ui.WishList.WishListFragment;
import com.tanvi.healthpal.ui.scanqr.apis.entity.elements.Nutrients;

import java.util.ArrayList;

public class WishlistAdapter extends RecyclerView.Adapter<wishlistHolder> {

    private ArrayList<Nutrients> items= new ArrayList<>();
    private ArrayList<Integer> selectedItems = new ArrayList<>();
    private LayoutInflater layoutInflater;
    private IWishListProdDetails iWishListProdDetails;

//    public WishlistAdapter(IWishListProdDetails iWishListProdDetails) {
//        this.iWishListProdDetails=iWishListProdDetails;
//    }


    @Override
    public wishlistHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        layoutInflater = LayoutInflater.from(parent.getContext());
        final View view = layoutInflater.inflate(R.layout.itemviewlist, parent, false);
        return new wishlistHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull wishlistHolder holder, int position) {
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(iWishListProdDetails != null){
                    iWishListProdDetails.remove(items.get(holder.getAdapterPosition()),holder.getAdapterPosition() );
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

        if(!isNullOrEmpty(items.get(holder.getAdapterPosition()).getProduct_iamge())){
            Picasso.get()
                    .load(items.get(holder.getAdapterPosition()).getProduct_iamge())
                    .placeholder(R.drawable.food_placeholder)
                    .error(R.drawable.food_placeholder)
                    .into(holder.prodImage, new Callback(){
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
       // holder.mcbCompare.setChecked(items.get(holder.getAdapterPosition()).setchecked());
        holder.mcbCompare.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(selectedItems.size() == 2 && isChecked){
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
    private boolean isNullOrEmpty(String str){
        if(str != null && !str.isEmpty()){
            return false;
        }
        return true;
    }
    public void setItemList(ArrayList<Nutrients> itemList){
        this.items = itemList;
        notifyDataSetChanged();
    }

    public void remove(int pos){
        items.remove(pos);
        notifyDataSetChanged();
    }

}
