package com.example.vendor.ui;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;

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


public class AddItemsFragment extends Fragment {

    private static final String TAG = "AddItemsFragment";

    private String title;
    private int page;
    private InvoiceDbHelper invoiceDbHelper;
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
        invoiceDbHelper = new InvoiceDbHelper(view.getContext());
        mAddItem = view.findViewById(R.id.editText);
        mAddItemPrice = view.findViewById(R.id.editText2);
        mAddItemQuantity = view.findViewById(R.id.editText3);
        mSubmitButton = view.findViewById(R.id.fab_submit_items);

        mSubmitButton.setOnClickListener(v -> insertItemsToDb());

        return view;
    }

    private void insertItemsToDb(){
        String itemName = mAddItem.getText().toString().trim();
        Double itemPrice = Double.parseDouble(mAddItemPrice.getText().toString().trim());
        int itemQuantity = Integer.parseInt(mAddItemQuantity.getText().toString().trim());

        SQLiteDatabase db = invoiceDbHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(InvoiceContract.ItemsEntry.COLUMN_ITEM_NAME, itemName);
        contentValues.put(InvoiceContract.ItemsEntry.COLUMN_AMOUNT, itemPrice);
        contentValues.put(InvoiceContract.ItemsEntry.COLUMN_QUANTITY, itemQuantity);

        long newRowId = db.insert(InvoiceContract.ItemsEntry.TABLE_NAME, null, contentValues);

        if(newRowId==1){
            Toast.makeText(getContext(), "Successfully Added Items", Toast.LENGTH_SHORT).show();
        } else if(newRowId==-1) {
            Toast.makeText(getContext(), "Error occurred, try again", Toast.LENGTH_SHORT).show();
        }

    }

}
