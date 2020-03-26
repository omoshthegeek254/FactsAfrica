package com.example.factsafrica.ui.adapter;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.factsafrica.ui.models.User;

import java.util.List;

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
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull VendorsAdapter.VendorViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
