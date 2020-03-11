package com.example.vendor.adapters;

import android.content.Context;
import android.graphics.Color;
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
       holder.bindInvoice(mInvoices.get(position));
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
      public void bindInvoice(Invoice invoice) {

          if(invoice.getInvoiceStatus().equals("1") || invoice.getInvoiceStatus().equals("1")){
              mInvoiceStatus.setTextColor(Color.parseColor("#ECB753"));
              mInvoiceStatus.setText(invoice.getInvoiceStatus());
          } else if(invoice.getInvoiceStatus().equals("2") || invoice.getInvoiceStatus().equals("2")){
              mInvoiceStatus.setTextColor(Color.parseColor("#0B6623"));
              mInvoiceStatus.setText(invoice.getInvoiceStatus());
          } else if(invoice.getInvoiceStatus().equals("3") || invoice.getInvoiceStatus().equals("3")){
              mInvoiceStatus.setTextColor(Color.parseColor("#FF0000"));
              mInvoiceStatus.setText(invoice.getInvoiceStatus());
          }

          mInvoiceId.setText(invoice.getBuyerId().toString());

          mInvoiceDate.setText(invoice.getDueDate());
//            mInvoiceStatus.setText(invoice.getStatus());
      }
  }
}
