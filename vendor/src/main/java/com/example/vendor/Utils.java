package com.example.vendor;

import android.content.Context;

import androidx.appcompat.app.AlertDialog;

import com.example.vendor.network.FactsAfricaApi;
import com.example.vendor.network.FactsAfricaClient;

public class Utils {

    public static FactsAfricaApi getApi(){
        return FactsAfricaClient.getClient().create(FactsAfricaApi.class);
    }

    public static AlertDialog showDialogMessage(Context context, String title, String message) {
        AlertDialog alertDialog = new AlertDialog.Builder(context).setTitle(title).setMessage(message).show();
        if (alertDialog.isShowing()) {
            alertDialog.cancel();
        }
        return alertDialog;
    }
}
