package com.example.factsafrica.ui.ui.dashboard;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.SearchView;
import android.widget.Toast;

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
    private static final String TAG = "DashBoardFragment";
    private String token;
    private SharedPreferences mPreference;

    @BindView(R.id.invoicesRecycler) RecyclerView mInvoicesRecycler;
    private List<Invoice> invoices;
    //InvoiceAdapter invoiceAdapter = new InvoiceAdapter(invoices, getContext());

    SearchView mSearch;



    private DashboardViewModel dashboardViewModel;

    private View rootView;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        dashboardViewModel = ViewModelProviders.of(this).get(DashboardViewModel.class);

        rootView = inflater.inflate(R.layout.fragment_dashboard, container, false);
        mPreference = PreferenceManager.getDefaultSharedPreferences(rootView.getContext());
        token = mPreference.getString("token", "");
//        mSearch = rootView.findViewById(R.id.invoiceSearch);
//        mSearch.setImeOptions(EditorInfo.IME_ACTION_DONE);
        ButterKnife.bind(this, rootView);
        getAllInvoices();
//        mSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                //invoiceAdapter.getFilter().filter(newText);
//                return false;
//            }
//        });
        return rootView;
    }

    public void getAllInvoices() {

        FactsAfricaApi service = FactsAfricaClient.getClient().create(FactsAfricaApi.class);
        Call<List<Invoice>> call = service.getAllInvoices(token);
        Log.v("URL", call.request().url().toString());
        call.enqueue(new Callback<List<Invoice>>() {
            @Override
            public void onResponse(Call<List<Invoice>> call, Response<List<Invoice>> response) {
                invoices = response.body();
                Log.d(TAG, "onResponse: "+invoices.get(0).getInvoiceAmount());
                InvoiceAdapter adapter = new InvoiceAdapter(invoices, rootView.getContext());
                LinearLayoutManager layoutManager = new LinearLayoutManager(rootView.getContext(), LinearLayoutManager.VERTICAL, false);
                mInvoicesRecycler.setLayoutManager(layoutManager);
                mInvoicesRecycler.setHasFixedSize(true);
                mInvoicesRecycler.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Invoice>> call, Throwable t) {
                Toast.makeText(getContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}