package com.example.vendor.ui.ui.buyers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.vendor.R;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.vendor.ui.BottomNavigation.EXTRA_DETAIL;

public class BuyerActivity extends AppCompatActivity {

    @BindView(R.id.textViewSupplierName)
    TextView mSupplierName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String supplierName = intent.getStringExtra(EXTRA_DETAIL);
        mSupplierName.setText(supplierName);

    }
}
