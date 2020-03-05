package com.example.factsafrica.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.factsafrica.R;
import com.example.factsafrica.ui.models.PurchaseOrder;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PurchaseOrderAdapter extends  RecyclerView.Adapter<PurchaseOrderAdapter.PurchaseOrderViewHolder> {

    private List<PurchaseOrder> purchaseorders;
    private Context mContext;

    public PurchaseOrderAdapter(List<PurchaseOrder> purchaseorders,Context mContext){
        this.purchaseorders = purchaseorders;
        this.mContext = mContext;

    }

    @NonNull
    @Override
    public PurchaseOrderAdapter.PurchaseOrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull PurchaseOrderAdapter.PurchaseOrderViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class PurchaseOrderViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.pOrder) TextView mOrderno;
        @BindView(R.id.pDate) TextView mDate;
        Context mContext;

        public PurchaseOrderViewHolder(@NonNull View itemView) {
            super(itemView);
            this.mContext = mContext;
            ButterKnife.bind(this, itemView);
        }

        public void bindPurchaseOrder(PurchaseOrder purchaseorder) {
            mOrderno.setText(purchaseorder.getOrderNumber());
            mDate.setText(purchaseorder.getPurchaseOrderDate());
        }
    }
}
