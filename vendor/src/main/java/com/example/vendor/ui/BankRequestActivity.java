package com.example.vendor.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vendor.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BankRequestActivity extends AppCompatActivity implements View.OnClickListener {


    //widgets
    @BindView(R.id.invoice_number_past_request)
    TextView invoiceNumberPastRequest;
    @BindView(R.id.business_name_past_request)
    TextView businessNamePastRequest;
    @BindView(R.id.date_today_past_request)
    TextView mDateToday;
    @BindView(R.id.item_one_past_request)
    TextView mItemOne;
    @BindView(R.id.item_price_past_request)
    TextView mItemPrice;
    @BindView(R.id.item_quantity_past_request)
    TextView mQuantity;
    @BindView(R.id.item_amount_past_request)
    TextView mItemAmount;
    @BindView(R.id.make_request_button)
    Button mMakeRequest;

    CheckBox mCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_request);
        ButterKnife.bind(this);
        mCheckBox = findViewById(R.id.contract_terms);
        mMakeRequest.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if(v==mMakeRequest)
        Toast.makeText(this, "Success!", Toast.LENGTH_SHORT).show();
    }
}
