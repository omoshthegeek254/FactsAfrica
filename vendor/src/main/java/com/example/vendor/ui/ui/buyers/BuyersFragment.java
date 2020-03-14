package com.example.vendor.ui.ui.buyers;

import android.content.Context;
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
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vendor.R;
import com.example.vendor.Utils;
import com.example.vendor.adapters.BuyersAdapter;
import com.example.vendor.models.User;
import com.example.vendor.ui.BottomNavigation;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.vendor.ui.BottomNavigation.EXTRA_ADDRESS;
import static com.example.vendor.ui.BottomNavigation.EXTRA_DETAIL;
import static com.example.vendor.ui.BottomNavigation.EXTRA_EMAIL;


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

    private BuyersFragmentListener listener;

    public interface BuyersFragmentListener{
        void onInputNameSent(String buyerName);
        void onInputEmailSent(String buyerEmail);
        void onInputAddressSent(String buyerAddress);
    }

    public void updateName(String name){
        mSupplierName.setText(name);
    }
    public void updateEmail(String email){
       mSupplierName.setText(email);
    }
    public void updateAddress(String address){
       mSupplierName.setText(address);
    }


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

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof BuyersFragmentListener){
            listener = (BuyersFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString() + "Must implement BuyerFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
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



//                        Intent intent = new Intent(getActivity(), BottomNavigation.class);
//                        String supplierName = mSupplierName.getText().toString();
//                        String supplierEmail = mSupplierEmail.getText().toString();
//                        String supplierAddress = mSupplierAddress.getText().toString();
//                        intent.putExtra(EXTRA_DETAIL, supplierName);
//                        intent.putExtra(EXTRA_EMAIL, supplierEmail);
//                        intent.putExtra(EXTRA_ADDRESS, supplierAddress);
//                        getActivity().startActivity(intent);

                        Log.d(TAG, "onResponse Buyer's Fragment: " + mSupplierName.getText().toString());
                        Log.d(TAG, "onResponse Buyer's Fragment: " + mSupplierEmail.getText().toString());
                        Log.d(TAG, "onResponse Buyer's Fragment: " + mSupplierAddress.getText().toString());
                        listener.onInputNameSent(mSupplierName.getText().toString());
                        listener.onInputEmailSent(mSupplierEmail.getText().toString());
                        listener.onInputAddressSent(mSupplierAddress.getText().toString());
                    });

                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {

            }
        });
    }

}