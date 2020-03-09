package com.example.vendor.network;



import com.example.vendor.models.Invoice;
import com.example.vendor.models.PurchaseOrder;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface FactsAfricaApi {
    @GET("invoices")
    Call<List<Invoice>> getAllInvoices();
    @GET("purchase_orders")
    Call<List<PurchaseOrder>> getAllOrders();
}
