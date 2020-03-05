package com.example.factsafrica.ui.network;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FactsAfricaClient {
    private static Retrofit retrofit = null;
    public static Retrofit getClient(){
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("https://dummy-api-facts-africa.herokuapp.com/")
                    .build();
        }
        return retrofit;
    }
}
