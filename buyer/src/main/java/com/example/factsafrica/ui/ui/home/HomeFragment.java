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

import com.example.factsafrica.R;
import com.example.factsafrica.ui.models.PurchaseOrder;
import com.example.factsafrica.ui.network.FactsAfricaApi;
import com.example.factsafrica.ui.network.FactsAfricaClient;

import java.util.List;

import butterknife.ButterKnife;
import retrofit2.Call;


public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        ButterKnife.bind(this, root);
        getOrders();
        return root;
    }
    public void getOrders() {


    }
}