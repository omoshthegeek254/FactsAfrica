package com.example.vendor.ui;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.vendor.R;
import com.example.vendor.db.InvoiceContract;
import com.example.vendor.db.InvoiceDbHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddressFragment extends Fragment {

    private static final String TAG = "AddressFragment";


    //private AddressFragmentListener addressFragmentListener;
    private InvoiceDbHelper invoiceDbHelper;
    private EditText personName;
    private EditText personEmail;
    private EditText personAddress;
    private EditText personPhoneNumber;
    private FloatingActionButton mFloatingActionButton;

    private String title;
    private int page;


    public AddressFragment() {
        // Required empty public constructor
    }



    public static AddressFragment newInstance(int page, String title){
        AddressFragment addressFragment = new AddressFragment();
        Bundle args = new Bundle();
        args.putInt("someInt",page);
        args.putString("someTitle", title);
        addressFragment.setArguments(args);
        return addressFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        title = getArguments().getString("someTitle");
        page = getArguments().getInt("someInt",0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_address, container, false);
        invoiceDbHelper = new InvoiceDbHelper(view.getContext());
        personName = view.findViewById(R.id.editTextPersonName);
        personEmail = view.findViewById(R.id.editTextPersonEmail);
        personAddress = view.findViewById(R.id.editTextPersonAddress);
        personPhoneNumber = view.findViewById(R.id.editTextPhoneNumber);

        mFloatingActionButton = view.findViewById(R.id.fab_submit_address);

        mFloatingActionButton.setOnClickListener(v -> insertIntoDb());

        return view;

    }

    private void insertIntoDb(){
        String foundPersonName = personName.getText().toString().trim();
        String foundPersonEmail = personEmail.getText().toString().trim();
        String foundPersonAddress = personAddress.getText().toString().trim();
        String foundPersonPhoneNumber = personPhoneNumber.getText().toString().trim();


        SQLiteDatabase db = invoiceDbHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(InvoiceContract.AddressEntry.COLUMN_ADDRESS, foundPersonAddress);
        contentValues.put(InvoiceContract.AddressEntry.COLUMN_EMAIL, foundPersonEmail);
        contentValues.put(InvoiceContract.AddressEntry.COLUMN_PHONE_NUMBER, foundPersonPhoneNumber);
        contentValues.put(InvoiceContract.AddressEntry.COLUMN_BUSINESS_NAME, foundPersonName);

        long newRowId = db.insert(InvoiceContract.AddressEntry.TABLE_NAME, null, contentValues);

        if(newRowId ==1){
            Toast.makeText(getContext(), "Error inserting", Toast.LENGTH_SHORT).show();
        } else{
            Toast.makeText(getContext(), "Address Saved! Swipe to add items to the invoice", Toast.LENGTH_LONG).show();
        }

    }

}
