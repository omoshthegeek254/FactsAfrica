package com.example.vendor.ui.ui.buyers;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vendor.R;
import com.example.vendor.Utils;
import com.example.vendor.adapters.BuyersAdapter;
import com.example.vendor.models.User;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.vendor.ui.BottomNavigation.EXTRA_DETAIL;


public class BuyersFragment extends Fragment {

    private String token;
    private SharedPreferences mPreference;
    private List<Integer> allIds = new ArrayList<>();
    private List<User> allBuyers;


    private BuyersViewModel buyersViewModel;
    private List<User> buyerList;
    private RecyclerView rvBuyers;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        buyersViewModel =
                ViewModelProviders.of(this).get(BuyersViewModel.class);


        View root = inflater.inflate(R.layout.fragment_buyers, container, false);
        //final TextView textView = root.findViewById(R.id.text_buyers);
        mPreference = PreferenceManager.getDefaultSharedPreferences(root.getContext());
        token = mPreference.getString("token", "");

        buyersViewModel.getText().observe(this, s -> {
            //textView.setText(s);
        });

        getUsersById();
        return root;
    }


    void getUsersById() {
        Call<List<User>> buyersId = Utils.getApi().getBuyerId(token);

        buyersId.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    allBuyers = response.body();
                    final RecyclerView rvBuyers = getView().findViewById(R.id.rvBuyers);
                    BuyersAdapter myAdapter = new BuyersAdapter(getActivity(), allBuyers);
                    rvBuyers.setLayoutManager(new GridLayoutManager(getActivity(), 2));
                    rvBuyers.setAdapter(myAdapter);
                    rvBuyers.setHasFixedSize(true);
                    myAdapter.setOnClickListener((view, position) -> {
                        TextView mSupplierName = view.findViewById(R.id.buyerName);
                        Intent intent = new Intent(getActivity(), BuyerActivity.class);
                        intent.putExtra(EXTRA_DETAIL, mSupplierName.getText().toString());
                        startActivity(intent);

                    });

                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {

            }
        });
    }

    void getBuyers() {
        for (int a : allIds) {
            Call<List<User>> buyersCall = Utils.getApi().getUserByRole(2, token);

            buyersCall.enqueue(new Callback<List<User>>() {
                @Override
                public void onResponse(Call<List<User>> call, Response<List<User>> response) {

                }


                @Override
                public void onFailure(Call<List<User>> call, Throwable t) {

                }
            });


        }

    }
}