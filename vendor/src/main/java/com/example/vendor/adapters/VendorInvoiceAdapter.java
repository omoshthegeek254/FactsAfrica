package com.example.vendor.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vendor.R;
import com.example.vendor.models.Invoice;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

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
       // holder.bindInvoice(mInvoices.get(position));
    }

    @Override
    public int getItemCount() {
        return mInvoices.size();
    }

  public class VendorInvoiceViewHolder extends RecyclerView.ViewHolder{
      @BindView(R.id.invoiceNo)
      TextView mInvoiceId;
      @BindView(R.id.invoice_date) TextView mInvoiceDate;
      @BindView(R.id.invoice_status) TextView mInvoiceStatus;
      Context mContext;
      public VendorInvoiceViewHolder(@NonNull View itemView) {
          super(itemView);
          this.mContext = mContext;
          ButterKnife.bind(this, itemView);
      }
  }
}
