package com.example.vendor.ui.ui.buyers;

import android.os.Bundle;
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


public class BuyersFragment extends Fragment {

    private BuyersViewModel buyersViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        buyersViewModel =
                ViewModelProviders.of(this).get(BuyersViewModel.class);
        View root = inflater.inflate(R.layout.fragment_buyers, container, false);
        final TextView textView = root.findViewById(R.id.text_buyers);
        buyersViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}