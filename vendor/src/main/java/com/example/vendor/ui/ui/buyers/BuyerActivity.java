package com.example.vendor.ui.ui.buyers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vendor.R;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.vendor.ui.BottomNavigation.EXTRA_ADDRESS;

import static com.example.vendor.ui.BottomNavigation.EXTRA_EMAIL;
import static com.example.vendor.ui.BottomNavigation.EXTRA_ID;
import static com.example.vendor.ui.BottomNavigation.EXTRA_LOGO;
import static com.example.vendor.ui.BottomNavigation.EXTRA_NAME;
import static com.example.vendor.ui.BottomNavigation.EXTRA_PHONE;

public class BuyerActivity extends AppCompatActivity {


    @BindView(R.id.textViewSupplierNameM) TextView mSupplierName;
    @BindView(R.id.buyerIdM) TextView mSupplierId;
    @BindView(R.id.emailM) TextView mSupplierEmail;
    @BindView(R.id.addressM) TextView mSupplierAddress;
    @BindView(R.id.phoneM) TextView mSupplierPhone;
    @BindView(R.id.logoM) ImageView mSupplierLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_buyer);
        setContentView(R.layout.fragment_buyer_detail);

        ButterKnife.bind(this);

        //handling intent data

        Intent intent = getIntent();
       int  supplierLogo = intent.getIntExtra(EXTRA_LOGO,0);
        String supplierName = intent.getStringExtra(EXTRA_NAME);
        String supplierId = intent.getStringExtra(EXTRA_ID);
        String supplierEmail = intent.getStringExtra(EXTRA_EMAIL);
        String supplierPhone= intent.getStringExtra(EXTRA_PHONE);
        String supplierAddress = intent.getStringExtra(EXTRA_ADDRESS);


        mSupplierLogo.setImageResource(supplierLogo);
        mSupplierId.setText(supplierId);
        mSupplierName.setText(supplierName);

        mSupplierEmail.setText(supplierEmail);
        mSupplierPhone.setText(supplierPhone);

        mSupplierAddress.setText(supplierAddress);




    }
}
