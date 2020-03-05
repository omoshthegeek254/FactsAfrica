package com.example.factsafrica.ui.network;

import com.example.factsafrica.ui.models.Invoice;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface FactsAfricaApi {
    @GET("invoices")
    Call<List<Invoice>> getAllInvoices();
}
