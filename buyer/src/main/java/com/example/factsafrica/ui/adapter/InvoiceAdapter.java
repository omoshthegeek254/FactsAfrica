package com.example.factsafrica.ui.adapter;

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

import com.example.factsafrica.R;
import com.example.factsafrica.ui.models.Invoice;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InvoiceAdapter extends RecyclerView.Adapter<InvoiceAdapter.InvoiceViewHolder> implements Filterable {
    private List<Invoice> invoices;
    private List<Invoice> invoiceList;
    private Context mContext;

    public InvoiceAdapter(List<Invoice> invoices, Context mContext) {
        this.invoices = invoices;
        this.mContext = mContext;
        invoiceList = new ArrayList<>(invoices);
    }

    @NonNull
    @Override
    public InvoiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.invoices_list, parent, false);
        InvoiceViewHolder viewHolder = new InvoiceViewHolder(view);
        return viewHolder;
    }

    @Override
    public int getItemCount() {
        return invoices.size();
    }

    @Override
    public void onBindViewHolder(@NonNull InvoiceViewHolder holder, int position) {
        holder.bindInvoice(invoices.get(position));
    }

    @Override
    public Filter getFilter() {
        return exampleInvoice;
    }

    private Filter exampleInvoice = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Invoice> filteredList = new ArrayList<>();
            if(constraint == null || constraint.length()==0){
                filteredList.addAll(invoiceList);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for(Invoice invoice: invoiceList){
                    if(invoice.getInvoiceStatus().toString().toLowerCase().contains(filterPattern)){
                        filteredList.add(invoice);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            invoices.clear();
            invoiceList.addAll((List)results.values);
            notifyDataSetChanged();

        }
    };

    public class InvoiceViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.invoiceNo) TextView mInvoiceId;
        @BindView(R.id.invoiceDate) TextView mInvoiceDate;
        @BindView(R.id.invoice_status) TextView mInvoiceStatus;
        @BindView(R.id.payAmount) TextView mPayAble;
        Context mContext;

        //illegal vars
        String currency = " Amount Ksh: ";
        String dueBy = "Due by: ";
        String invoiceNumber = " Invoice Number: ";

        public InvoiceViewHolder(@NonNull View itemView) {
            super(itemView);
            this.mContext = mContext;
            ButterKnife.bind(this, itemView);
        }

        public void bindInvoice(Invoice invoice) {

            if(invoice.getInvoiceStatus().toString().equals("1")){
                mInvoiceStatus.setTextColor(Color.parseColor("#ECB753"));
                mInvoiceStatus.setText(invoice.getInvoiceStatus().toString());
                mInvoiceStatus.setText("pending");
            } else if(invoice.getInvoiceStatus().toString().equals("2")){
                mInvoiceStatus.setTextColor(Color.parseColor("#0B6623"));
                mInvoiceStatus.setText(invoice.getInvoiceStatus().toString());
                mInvoiceStatus.setText("approved");
            } else if(invoice.getInvoiceStatus().toString().equals("3")){
                mInvoiceStatus.setTextColor(Color.parseColor("#FF0000"));
                mInvoiceStatus.setText(invoice.getInvoiceStatus().toString());
                mInvoiceStatus.setText("declined");
            }

            mInvoiceId.setText(invoiceNumber + invoice.getBuyerId().toString());
            mPayAble.setText(currency + invoice.getInvoiceAmount());

            mInvoiceDate.setText(dueBy + invoice.getDueDate());
//            mInvoiceStatus.setText(invoice.getStatus());
        }
    }
}
