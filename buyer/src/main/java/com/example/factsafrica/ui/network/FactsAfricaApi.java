package com.example.factsafrica.ui.network;

import com.example.factsafrica.ui.models.Invoice;
import com.example.factsafrica.ui.models.LoginBuyer;
import com.example.factsafrica.ui.models.PurchaseOrder;
import com.example.factsafrica.ui.models.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface FactsAfricaApi {
    @POST("login")
    Call<User> login(@Body LoginBuyer login,
                     @Header("Accept") String acceptType);
    @GET("invoice")
    Call<List<Invoice>> getAllInvoices();
    @GET("purchase_orders")
    Call<List<PurchaseOrder>> getAllOrders();
    //post
    @POST("invoice/update/{id}")
    Call<List<Invoice>>setReviewed(@Header("Authorization") String bearerToken,
                                   @Body Invoice invoice);
}
