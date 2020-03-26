package com.example.factsafrica.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.factsafrica.R;
import com.example.factsafrica.ui.models.User;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VendorsAdapter extends RecyclerView.Adapter<VendorsAdapter.VendorViewHolder> {
    private List<User> vendors;
    private Context mContext;

    public VendorsAdapter(List<User> vendors,Context mContext){
        this.vendors = vendors;
        this.mContext= mContext;
    }


    @NonNull
    @Override
    public VendorsAdapter.VendorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.vendors_card, parent, false);
        VendorViewHolder viewHolder = new VendorViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull VendorsAdapter.VendorViewHolder holder, int position) {
        holder.bindVendor(vendors.get(position));
    }

    @Override
    public int getItemCount() {
        return vendors.size();
    }



    //view holder class
    public class VendorViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.vendor_name) TextView mName;
        @BindView(R.id.vendor_email) TextView mMail;
        @BindView(R.id.vendor_address) TextView mAddress;

        Context mContext;

        public VendorViewHolder(@NonNull View itemView) {
            super(itemView);
            this.mContext = mContext;
            ButterKnife.bind(this, itemView);
        }

        public void bindVendor(User vendor) {
          mName.setText(vendor.getName());
            mMail.setText(vendor.getEmail());
            mAddress.setText(vendor.getCreatedAt());
        }
    }
}
