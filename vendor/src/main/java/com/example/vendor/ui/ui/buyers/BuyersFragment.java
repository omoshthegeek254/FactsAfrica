package com.example.vendor.ui.ui.buyers;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vendor.R;
import com.example.vendor.Utils;
import com.example.vendor.adapters.BuyersAdapter;
import com.example.vendor.models.User;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class BuyersFragment extends Fragment {

    private static final String TAG = "BuyersFragment";

    private TextView mSupplierName;
    private TextView mSupplierEmail;
    private TextView mSupplierAddress;

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

        mSupplierName = root.findViewById(R.id.business_name_hardcoded);
        mSupplierEmail = root.findViewById(R.id.business_email_hardcoded);
        mSupplierAddress = root.findViewById(R.id.business_address_one_hardcoded);

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
                    rvBuyers.setLayoutManager(new LinearLayoutManager(getActivity()));
                    rvBuyers.setAdapter(myAdapter);
                    rvBuyers.setHasFixedSize(true);
                    myAdapter.setOnClickListener((view, position) -> {
                        Snackbar snackbar = Snackbar
                                .make(view, allBuyers.get(position).getName(), Snackbar.LENGTH_LONG);
                        snackbar.show();

//                        Intent intent = new Intent(getActivity(), BottomNavigation.class);
//                        String supplierName = mSupplierName.getText().toString();
//                        String supplierEmail = mSupplierEmail.getText().toString();
//                        String supplierAddress = mSupplierAddress.getText().toString();
//                        intent.putExtra(EXTRA_DETAIL, supplierName);
//                        intent.putExtra(EXTRA_EMAIL, supplierEmail);
//                        intent.putExtra(EXTRA_ADDRESS, supplierAddress);
//                        getActivity().startActivity(intent);
                    });

                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {

            }
        });
    }

}