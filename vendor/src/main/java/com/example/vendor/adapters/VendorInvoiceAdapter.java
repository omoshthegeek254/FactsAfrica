package com.example.vendor.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vendor.R;
import com.example.vendor.models.Invoice;

import java.util.ArrayList;
import java.util.List;

public class VendorInvoiceAdapter extends RecyclerView.Adapter<VendorInvoiceAdapter.VendorInvoiceViewHolder> implements Filterable {
   private List<Invoice> mInvoices;
   private List<Invoice>mInvoiceList;
   private Context mContext;

   public VendorInvoiceAdapter(List<Invoice> mInvoices,Context mContext){
       this.mInvoices=mInvoices;
       this.mContext=mContext;
       mInvoiceList= new ArrayList<>(mInvoices);
   }

    @Override
    public Filter getFilter() {
        return null;
    }

    @NonNull
    @Override
    public VendorInvoiceAdapter.VendorInvoiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.invoices_list, parent, false);
        VendorInvoiceViewHolder viewHolder = new VendorInvoiceViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull VendorInvoiceAdapter.VendorInvoiceViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

  public class VendorInvoiceViewHolder extends RecyclerView.ViewHolder{
      public VendorInvoiceViewHolder(@NonNull View itemView) {
          super(itemView);
      }
  }
}
