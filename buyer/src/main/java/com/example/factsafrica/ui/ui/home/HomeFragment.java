package com.example.factsafrica.ui.ui.home;

import android.os.Bundle;
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
import com.example.factsafrica.ui.models.PurchaseOrder;
import com.example.factsafrica.ui.network.FactsAfricaApi;
import com.example.factsafrica.ui.network.FactsAfricaClient;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment {
    @BindView(R.id.homeRecycler) RecyclerView mOrderRecycler;
    private List<PurchaseOrder> purchaseorders;
    private View root;

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
         root = inflater.inflate(R.layout.fragment_home, container, false);

        ButterKnife.bind(this, root);
        //getOrders();

        return root;

    }
    // request and get order//

//    public void getOrders() {
//        FactsAfricaApi service = FactsAfricaClient.getClient().create(FactsAfricaApi.class);
//        Call<List<PurchaseOrder>> call = service.getAllOrders();
//        call.enqueue(new Callback<List<PurchaseOrder>>() {
//            @Override
//            public void onResponse(Call<List<PurchaseOrder>> call, Response<List<PurchaseOrder>> response) {
//                purchaseorders = response.body();
//                PurchaseOrderAdapter adapter = new PurchaseOrderAdapter(purchaseorders, root.getContext());
//                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(root.getContext(), LinearLayoutManager.VERTICAL, false);
//                mOrderRecycler.setLayoutManager(layoutManager);
//                mOrderRecycler.setHasFixedSize(true);
//                mOrderRecycler.setAdapter(adapter);
//                adapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onFailure(Call<List<PurchaseOrder>> call, Throwable t) {
//
//            }
//        });
//    }
}