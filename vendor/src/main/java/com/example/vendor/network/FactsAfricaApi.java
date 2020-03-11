package com.example.vendor.network;


import com.example.vendor.models.Invoice;
import com.example.vendor.models.Login;
import com.example.vendor.models.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface FactsAfricaApi {

    @POST("login")
    Call<User> login(@Body Login login,
                     @Header("Accept") String acceptType);

    @GET("loggeduser")
    Call<User> getUser(@Header("Authorization") String bearerToken);

    @GET("/invoices")
    Call<List<Invoice>> getAllInvoices();
    @GET("/user/2")
    Call<List<User>> getUserByRole();
}
