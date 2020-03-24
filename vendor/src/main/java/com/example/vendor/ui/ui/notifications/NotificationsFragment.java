package com.example.vendor.ui.ui.notifications;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.vendor.Constants;
import com.example.vendor.R;
import com.example.vendor.adapters.ApprovedInvoicesAdapter;
import com.example.vendor.adapters.VendorInvoiceAdapter;
import com.example.vendor.models.Invoice;
import com.example.vendor.network.FactsAfricaApi;
import com.example.vendor.network.FactsAfricaClient;
import com.example.vendor.ui.BankRequestActivity;
import com.example.vendor.ui.ui.buyers.BuyersViewModel;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationsFragment extends Fragment {


    private String token;
    private SharedPreferences mPreference;
    @BindView(R.id.approved_requests_recycler_view)
    RecyclerView mInvoicesRecycler;
    private List<Invoice> invoices;
    private View root;

    private BuyersViewModel notificationsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                ViewModelProviders.of(this).get(BuyersViewModel.class);
        root = inflater.inflate(R.layout.fragment_notifications, container, false);
        ButterKnife.bind(this, root);
        mPreference = PreferenceManager.getDefaultSharedPreferences(root.getContext());
        token = mPreference.getString("token", "");
        displayApprovedInvoices();
        return root;
    }
    public void displayApprovedInvoices(){
        FactsAfricaApi service = FactsAfricaClient.getClient().create(FactsAfricaApi.class);
        Call<List<Invoice>> call = service.getApprovedInvoices(token, 4);
        call.enqueue(new Callback<List<Invoice>>() {
            @Override
            public void onResponse(Call<List<Invoice>> call, Response<List<Invoice>> response) {
                invoices = response.body();
                ApprovedInvoicesAdapter adapter = new ApprovedInvoicesAdapter(invoices,root.getContext());
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(root.getContext(), LinearLayoutManager.VERTICAL, false);
                mInvoicesRecycler.setAdapter(adapter);
                mInvoicesRecycler.setLayoutManager(linearLayoutManager);
                mInvoicesRecycler.setHasFixedSize(true);
                adapter.notifyDataSetChanged();
                adapter.setOnClickListener((View view, int position) -> {
                    //Toast.makeText(getContext(), invoices.get(position).getInvoiceAmount(), Toast.LENGTH_SHORT).show();
                    TextView invoiceName = root.findViewById(R.id.invoiceNo);
                    TextView invoiceDate = root.findViewById(R.id.invoice_date);
                    TextView amountPaid = root.findViewById(R.id.payAmount);

                    Intent intent = new Intent(getActivity(), BankRequestActivity.class);
                    intent.putExtra(Constants.NAME, invoiceName.getText().toString().trim());
                    intent.putExtra(Constants.DATE, invoiceDate.getText().toString().trim());
                    intent.putExtra(Constants.AMOUNT_PAID, amountPaid.getText().toString().trim());
                    startActivity(intent);

                });
            }
            @Override
            public void onFailure(Call<List<Invoice>> call, Throwable t) {
                Toast.makeText(getContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }



}