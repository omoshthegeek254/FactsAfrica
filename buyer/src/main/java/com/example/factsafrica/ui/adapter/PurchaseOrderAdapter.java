package com.example.factsafrica.ui.adapter;

import android.content.Context;

import com.example.factsafrica.ui.models.PurchaseOrder;

import java.util.List;

public class PurchaseOrderAdapter {

    private List<PurchaseOrder> purchaseorders;
    private Context mContext;

    public PurchaseOrderAdapter(List<PurchaseOrder> purchaseorders,Context mContext){
        this.purchaseorders = purchaseorders;
        this.mContext = mContext;

    }

}
