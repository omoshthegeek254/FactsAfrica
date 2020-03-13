package com.example.vendor.ui;


import android.os.Bundle;
import android.os.Parcel;
import android.view.LayoutInflater;
import android.view.View;

import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.vendor.R;
import com.example.vendor.models.Invoice;

import org.parceler.Parcels;

/**
 * A simple {@link Fragment} subclass.
 */
public class InvoiceFragment extends Fragment {

    private Invoice mInvoice;
    private String title;
    private int page;


    public InvoiceFragment() {
        // Required empty public constructor
    }

    public static InvoiceFragment newInstance(int page, String title){
        InvoiceFragment invoiceFragment = new InvoiceFragment();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        invoiceFragment.setArguments(args);
        return invoiceFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("someInt", 0);
        title = getArguments().getString("someTitle");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_invoice, container, false);
    }

}
