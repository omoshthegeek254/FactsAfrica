package com.example.vendor.ui.ui.buyers;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.example.vendor.Constants;
import com.example.vendor.Utils;
import com.example.vendor.models.User;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BuyerPresenter {


    private BuyersView buyersView;


    private static final String TAG = "BuyerPresenter";
    private Context context;

    public BuyerPresenter(BuyersView buyersView) {
        this.buyersView = buyersView;

    }







}
