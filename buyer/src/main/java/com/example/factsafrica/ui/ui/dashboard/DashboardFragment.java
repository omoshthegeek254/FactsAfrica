package com.example.factsafrica.ui.ui.dashboard;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.factsafrica.R;
import com.example.factsafrica.ui.adapter.InvoiceAdapter;
import com.example.factsafrica.ui.models.Invoice;
import com.example.factsafrica.ui.network.FactsAfricaApi;
import com.example.factsafrica.ui.network.FactsAfricaClient;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DashboardFragment extends Fragment {

    @BindView(R.id.invoicesRecycler) RecyclerView mInvoicesRecycler;
    private List<Invoice> invoices;

    private DashboardViewModel dashboardViewModel;

    private View rootView;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        dashboardViewModel = ViewModelProviders.of(this).get(DashboardViewModel.class);

        rootView = inflater.inflate(R.layout.fragment_dashboard, container, false);


//        final TextView textView = rootView.findViewById(R.id.text_dashboard);
//        dashboardViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        ButterKnife.bind(this, rootView);
        getInvoices();
        return rootView;
    }

    public void getInvoices() {

        FactsAfricaApi service = FactsAfricaClient.getClient().create(FactsAfricaApi.class);
        Call<List<Invoice>> call = service.getAllInvoices();
        Log.v("URL", call.request().url().toString());
        call.enqueue(new Callback<List<Invoice>>() {
            @Override
            public void onResponse(Call<List<Invoice>> call, Response<List<Invoice>> response) {
                invoices = response.body();
                InvoiceAdapter adapter = new InvoiceAdapter(invoices, rootView.getContext());
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(rootView.getContext(), LinearLayoutManager.VERTICAL, false);
                mInvoicesRecycler.setLayoutManager(layoutManager);
                mInvoicesRecycler.setHasFixedSize(true);
                mInvoicesRecycler.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Invoice>> call, Throwable t) {

            }
        });
    }
}