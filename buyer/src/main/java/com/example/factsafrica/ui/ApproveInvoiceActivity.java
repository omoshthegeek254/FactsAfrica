package com.example.factsafrica.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.factsafrica.R;
import com.example.factsafrica.ui.models.Invoice;
import com.example.factsafrica.ui.models.Status;
import com.example.factsafrica.ui.network.FactsAfricaApi;
import com.example.factsafrica.ui.network.FactsAfricaClient;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApproveInvoiceActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "ApproveInvoiceActivity";

    @BindView(R.id.item_one_past_request)
    TextView mItemOne;
    @BindView(R.id.item_price_past_request)
    TextView mItemPrice;
    @BindView(R.id.item_quantity_past_request)
    TextView mQuantity;
    @BindView(R.id.item_amount_past_request)
    TextView mAmount;
    @BindView(R.id.btn_approve_invoice)
    Button mApproveInvoiceButton;
    @BindView(R.id.btn_decline_invoice)
    Button mDeclineInvoiceButton;
    @BindView(R.id.invoice_status_buyer)
    TextView mInvoiceStatus;

    private String token;
    private SharedPreferences mPreferences;
    int position;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approve_invoice);
        mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        token = mPreferences.getString("token", "");
        ButterKnife.bind(this);
        mApproveInvoiceButton.setOnClickListener(this);
        mDeclineInvoiceButton.setOnClickListener(this);

        Intent intent = getIntent();
        String status = intent.getStringExtra(ConstantsBuyer.INVOICE_STATUS);
        position = intent.getIntExtra(ConstantsBuyer.INVOICE_POSITION, 2);
        Log.d(TAG, "onStatus: "+status);
        mInvoiceStatus.setText(status);

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    public void approveInvoice(){
        FactsAfricaApi service = FactsAfricaClient.getClient().create(FactsAfricaApi.class);
        Status status = new Status("2");
        Call<Invoice> call = service.updateStatus(token, position,status);
        call.enqueue(new Callback<Invoice>() {
            @Override
            public void onResponse(Call<Invoice> call, Response<Invoice> response) {
                Invoice invoice = response.body();
                Toast.makeText(ApproveInvoiceActivity.this, "Invoice Approved!", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onResponseStatus: "+response.code());

            }

            @Override
            public void onFailure(Call<Invoice> call, Throwable t) {
                Toast.makeText(ApproveInvoiceActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
    public void declineInvoice(){
        Status status = new Status("3");
        FactsAfricaApi service = FactsAfricaClient.getClient().create(FactsAfricaApi.class);
        Call<Invoice> call = service.updateStatus(token, position, status);
        call.enqueue(new Callback<Invoice>() {
            @Override
            public void onResponse(Call<Invoice> call, Response<Invoice> response) {
                Invoice invoice = response.body();
                Log.d(TAG, "onResponse Decline: "+response.code());
                Toast.makeText(ApproveInvoiceActivity.this, "Invoice Declined", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Invoice> call, Throwable t) {
                Toast.makeText(ApproveInvoiceActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public void onClick(View v) {
        if(v==mApproveInvoiceButton){
            approveInvoice();
        }
        if(v==mDeclineInvoiceButton){
            declineInvoice();
        }

    }
}
