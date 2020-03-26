package com.example.vendor.network;


import com.example.vendor.models.Invoice;
import com.example.vendor.models.InvoicePosted;
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

    @POST("invoice")
    Call<Invoice>postInvoice(@Header("Authorization") String bearerToken,
                             @Body InvoicePosted invoicePosted);

    @GET("supplier/buyers")
    Call<List<User>> getBuyerId(@Header("Authorization") String bearerToken);

    @GET("user/{id}")
    Call<List<User>> getUserByRole(@Path("id") int id,
                                   @Header("Authorization") String bearerToken
    );
    @GET("supplier/invoices")
    Call<List<Invoice>> getAllInvoices(@Header("Authorization") String bearerToken);

    @GET("user/{id}/invoices/approved")
    Call<List<Invoice>> getApprovedInvoices(@Header("Authorization") String bearerToken,
                                            @Path("id")int id);

}
