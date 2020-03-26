package com.example.factsafrica.ui;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.factsafrica.R;
import com.example.factsafrica.ui.db.InvoiceDbHelper;

import butterknife.BindView;

public class ApproveInvoiceActivity extends AppCompatActivity {

    InvoiceDbHelper dbHelper;
    @BindView(R.id.item_one_past_request)
    TextView mItemOne;
    @BindView(R.id.item_price_past_request)
    TextView mItemPrice;
    @BindView(R.id.item_quantity_past_request)
    TextView mQuantity;
    @BindView(R.id.item_amount_past_request)
    TextView mAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approve_invoice);
        dbHelper = new InvoiceDbHelper(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

}
