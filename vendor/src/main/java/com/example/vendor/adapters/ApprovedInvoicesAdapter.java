package com.example.vendor.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vendor.R;
import com.example.vendor.models.Invoice;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ApprovedInvoicesAdapter extends RecyclerView.Adapter<ApprovedInvoicesAdapter.ViewHolder> {

    private List<Invoice> mInvoices;
    private Context mContext;
    private static OnClickListener clickListener;

    public ApprovedInvoicesAdapter(List<Invoice> mInvoices,Context mContext){
        this.mInvoices=mInvoices;
        this.mContext=mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.invoices_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindInvoice(mInvoices.get(position));
    }

    @Override
    public int getItemCount() {
        return mInvoices.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @BindView(R.id.invoiceNo)
        TextView mInvoiceId;
        @BindView(R.id.payAmount) TextView mPayAmount;
        @BindView(R.id.invoice_date) TextView mInvoiceDate;
        @BindView(R.id.invoice_status) TextView mInvoiceStatus;
        Context mContext;
        String currency = " Amount Ksh: ";
        String dueBy = "Due by: ";
        String invoiceNumber = " Invoice Number: ";

        public ViewHolder(@NonNull View itemView) {
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
            mPayAmount.setText(currency + invoice.getInvoiceAmount());

            mInvoiceDate.setText(dueBy + invoice.getDueDate());
//            mInvoiceStatus.setText(invoice.getStatus());
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            clickListener.onClick(v, getAdapterPosition());
        }
    }
    public void setOnClickListener(OnClickListener clickListener){
        ApprovedInvoicesAdapter.clickListener = clickListener;
    }

    public interface OnClickListener {
        void onClick(View view, int position);
    }
}
