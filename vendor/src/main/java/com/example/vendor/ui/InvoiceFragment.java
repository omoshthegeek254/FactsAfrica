package com.example.vendor.ui;


import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Parcel;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.vendor.R;
import com.example.vendor.models.Invoice;
import com.example.vendor.ui.ui.buyers.BuyersFragment;

import org.parceler.Parcels;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class InvoiceFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "InvoiceFragment";


    //BindViews
    @BindView(R.id.date_today)
    TextView mPickDate;
    @BindView(R.id.business_name_details)
    TextView mBusinessName;
    @BindView(R.id.business_email)
    TextView mBusinessEmail;
    @BindView(R.id.business_address)
    TextView mBusinessAddress;
    @BindView(R.id.add_client)
    TextView mAddClient;
    @BindView(R.id.item_one)
    TextView mItemOne;
    @BindView(R.id.price_one)
    TextView mPriceOne;
    @BindView(R.id.quantity_one)
    TextView mQuantityOne;


    private Invoice mInvoice;
    private String title;
    private int page;
    private View rootView;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    //private InvoiceFragmentListener listener;

    //Calender
   private Calendar myCal = Calendar.getInstance();

   private int year =myCal.get(Calendar.YEAR);
   private int month = myCal.get(Calendar.MONTH);
   private int day = myCal.get(Calendar.DAY_OF_WEEK);





    public InvoiceFragment() {
        // Required empty public constructor
    }

//    public interface InvoiceFragmentListener{
//        void updateName(CharSequence name);
//        void updateEmail(CharSequence email);
//        void updateAddress(CharSequence address);
//    }
//
//    public void updateNameField(CharSequence newName){
//        mBusinessName.setText(newName);
//    }
//    public void updateEmailField(CharSequence newEmail){
//        mBusinessEmail.setText(newEmail);
//    }
//    public void updateAddressField(CharSequence newAddress){
//        mBusinessAddress.setText(newAddress);
//    }

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

        mDateSetListener = (datePicker, year, month, day) -> {
            month = month+1;

            String date = month  + "/" + day + "/" + year;


            mPickDate.setText(date);

        };
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        rootView = inflater.inflate(R.layout.fragment_invoice, container, false);
        ButterKnife.bind(this, rootView);

        Log.d(TAG, "onCreateView: "+mBusinessName.getText().toString().trim());

        mPickDate.setOnClickListener(this);
        mAddClient.setOnClickListener(this);

        Bundle args = getArguments();
        String personName = args.getString("foundPersonName");
        String personEmail = args.getString("foundPersonEmail");
        String personAddress = args.getString("foundPersonAddress");
//
         Log.d(TAG, "onCreateView: abc "+personName);
//
        String item = args.getString("item");
        Double price = args.getDouble("price");
        Integer quantity = args.getInt("quantity");
//
//
        mBusinessName.setText(personName);
        mBusinessEmail.setText(personEmail);
        mBusinessAddress.setText(personAddress);
//
        mItemOne.setText(item);
        mPriceOne.setText(price.toString().trim());
        mQuantityOne.setText(quantity.toString().trim());

        Log.d(TAG, "onCreateView: abcd"+quantity);

//        listener.updateName(mBusinessName.getText().toString().trim());
//        listener.updateEmail(mBusinessEmail.getText().toString().trim());
//        listener.updateAddress(mBusinessAddress.getText().toString().trim());

        return rootView;
    }

    @Override
    public void onClick(View v) {
        DatePickerDialog dialog = new DatePickerDialog(
                getActivity(),
                android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                mDateSetListener,
                year,month,day);
        if(v==mPickDate){
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();

        }

    }

//    @Override
//    public void onAttach(@NonNull Context context) {
//        super.onAttach(context);
//        if(context instanceof InvoiceFragmentListener){
//            listener = (InvoiceFragmentListener) context;
//        } else {
//            throw new RuntimeException(context.toString() + "Must Implement Invoice Fragment Interface");
//        }
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        listener = null;
//    }

    //date picker



}
