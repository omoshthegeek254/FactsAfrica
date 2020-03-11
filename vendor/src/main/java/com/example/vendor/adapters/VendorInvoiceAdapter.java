package com.example.vendor.adapters;

import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class VendorInvoiceAdapter extends RecyclerView.Adapter<VendorInvoiceAdapter.VendorInvoiceViewHolder> implements Filterable {
    @Override
    public Filter getFilter() {
        return null;
    }

    @NonNull
    @Override
    public VendorInvoiceAdapter.VendorInvoiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull VendorInvoiceAdapter.VendorInvoiceViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

  public class VendorInvoiceViewHolder extends RecyclerView.ViewHolder{}
}
