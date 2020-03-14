package com.example.vendor.ui;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.vendor.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class AddItemsFragment extends Fragment {

    private static final String TAG = "AddItemsFragment";

    private String title;
    private int page;

    private EditText mAddItem;
    private EditText mAddItemPrice;
    private EditText mAddItemQuantity;
    private FloatingActionButton mSubmitButton;


    public AddItemsFragment() {
        // Required empty public constructor
    }

    public static AddItemsFragment newInstance(int page, String title){
        AddItemsFragment addItemsFragment = new AddItemsFragment();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        addItemsFragment.setArguments(args);
        return addItemsFragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        title = getArguments().getString("someTitle");
        page = getArguments().getInt("someInt",0);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_add_items, container, false);
        mAddItem = view.findViewById(R.id.editText);
        mAddItemPrice = view.findViewById(R.id.editText2);
        mAddItemQuantity = view.findViewById(R.id.editText3);
        mSubmitButton = view.findViewById(R.id.fab_submit_items);

        mSubmitButton.setOnClickListener(v -> {
            String itemName = mAddItem.getText().toString().trim();
            Double itemPrice = Double.parseDouble(mAddItemPrice.getText().toString().trim());
            int itemQuantity = Integer.parseInt(mAddItemQuantity.getText().toString().trim());

            InvoiceFragment invoiceFragment = new InvoiceFragment();

            Bundle args = new Bundle();
            args.putString("item", itemName);
            args.putDouble("price",itemPrice);
            args.putInt("quantity", itemQuantity);


            invoiceFragment.setArguments(args);

            Log.d(TAG, "onCreateView: "+itemName);
            Log.d(TAG, "onCreateView: "+itemPrice);
            Log.d(TAG, "onCreateView: "+itemQuantity);

        });

        return view;
    }

}
