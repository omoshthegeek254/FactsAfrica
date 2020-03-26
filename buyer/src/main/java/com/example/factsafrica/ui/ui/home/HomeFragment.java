package com.example.factsafrica.ui.ui.home;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.factsafrica.R;
import com.example.factsafrica.ui.adapter.PurchaseOrderAdapter;
import com.example.factsafrica.ui.adapter.VendorsAdapter;
import com.example.factsafrica.ui.models.PurchaseOrder;
import com.example.factsafrica.ui.models.User;
import com.example.factsafrica.ui.network.FactsAfricaApi;
import com.example.factsafrica.ui.network.FactsAfricaClient;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment {

    private String token;
    private SharedPreferences mPreference;
    @BindView(R.id.homeRecycler) RecyclerView mVendorRecycler;
    private List<User> mVendors;
    private HomeViewModel homeViewModel;
    private View root;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
         root = inflater.inflate(R.layout.fragment_home, container, false);
        mPreference = PreferenceManager.getDefaultSharedPreferences(root.getContext());
        token = mPreference.getString("token", "");
        ButterKnife.bind(this, root);

        getVendors(); //call get Vendors

        return root;

    }
    // request and get Vendors//

    public void getVendors() {
        FactsAfricaApi service = FactsAfricaClient.getClient().create(FactsAfricaApi.class);
       // call
        Call<List<User>> call = service.getVendors(token);
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                mVendors = response.body();
                VendorsAdapter adapter = new VendorsAdapter(mVendors, root.getContext());
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(root.getContext(), LinearLayoutManager.VERTICAL, false);
                mVendorRecycler.setLayoutManager(layoutManager);
                mVendorRecycler.setHasFixedSize(true);
                mVendorRecycler.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {

            }
        });
    }
}