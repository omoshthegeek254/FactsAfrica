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
import retrofit2.http.Path;

public interface FactsAfricaApi {

    @POST("login")
    Call<User> login(@Body Login login,
                     @Header("Accept") String acceptType);

    @GET("loggeduser")
    Call<User> getUser(@Header("Authorization") String bearerToken);

    @GET("invoice/buyers")
    Call<List<User>> getBuyerId(@Header("Authorization") String bearerToken);

    @GET("user/{id}")
    Call<List<User>> getUserByRole(@Path("id") int id,
                                   @Header("Authorization") String bearerToken

    );
    @GET("invoices")
    Call<List<Invoice>> getAllInvoices(@Header("Authorization") String bearerToken);
    @GET("user/2")
    Call<List<User>> getUserByRole();
}
