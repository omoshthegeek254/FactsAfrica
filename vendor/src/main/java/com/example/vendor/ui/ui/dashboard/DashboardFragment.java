package com.example.vendor.ui.ui.dashboard;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.vendor.R;
import com.example.vendor.ui.LoginActivity;

import java.util.prefs.Preferences;


public class DashboardFragment extends Fragment {
    private String token;
    private SharedPreferences mPreferences;

    private DashboardViewModel dashboardViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel = ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        mPreferences = PreferenceManager.getDefaultSharedPreferences(root.getContext());
        token = mPreferences.getString("token", "");
        Log.v("passed token", token);
        return root;
    }
}