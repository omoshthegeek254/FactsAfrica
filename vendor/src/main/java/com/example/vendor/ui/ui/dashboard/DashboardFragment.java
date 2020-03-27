package com.example.vendor.ui.ui.dashboard;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vendor.Constants;
import com.example.vendor.R;
import com.example.vendor.Utils;
import com.example.vendor.adapters.BuyersAdapter;
import com.example.vendor.adapters.VendorInvoiceAdapter;
import com.example.vendor.models.Invoice;
import com.example.vendor.models.Login;
import com.example.vendor.models.User;
import com.example.vendor.network.FactsAfricaApi;
import com.example.vendor.network.FactsAfricaClient;
import com.example.vendor.ui.BottomNavigation;
import com.example.vendor.ui.LoginActivity;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;
import java.util.prefs.Preferences;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DashboardFragment extends Fragment {

//    private BuyersAdapter adapter = new BuyersAdapter();
//    private VendorInvoiceAdapter adapter1 = new VendorInvoiceAdapter();

    private static final String TAG = "DashboardFragment";

    private String mEmail;
    List<User> userData;
    User userInfo;


    @BindView(R.id.account_user_name)
    TextView accUserName;
    @BindView(R.id.acc_user_email)
    TextView mUserEmail;
    @BindView(R.id.acc_user_phone)
    TextView mUserPhone;
    @BindView(R.id.acc_user_address)
    TextView mUserAddress;

    @BindView(R.id.buyer_size)
    TextView mBuyerSize;
    @BindView(R.id.total_invoices_sent)
    TextView mInvoicesNumber;

    @BindView(R.id.logout_image)
    ImageView mLogout;
    private String token;
    private SharedPreferences mPreferences;
    private DashboardViewModel dashboardViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel = ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        mPreferences = PreferenceManager.getDefaultSharedPreferences(root.getContext());
        mEmail = mPreferences.getString(Constants.PREFERENCES_EMAIL_KEY, null);
        Log.d(TAG, "onCreate: "+ mEmail);
        ButterKnife.bind(this, root);

        token = mPreferences.getString("token", "");
        Log.v("passed token", token);
        return root;


    }
    @Override
    public void onStart() {
        super.onStart();
        getAllInvoices();
        getUsersById();
        getUserInfo();
        logout();

    }

    private void getAllInvoices() {

        FactsAfricaApi service = FactsAfricaClient.getClient().create(FactsAfricaApi.class);
        Call<List<Invoice>> call = service.getAllInvoices(token);
        Log.v("URL1", call.request().url().toString());
        call.enqueue(new Callback<List<Invoice>>() {
            @Override
            public void onResponse(Call<List<Invoice>> call, Response<List<Invoice>> response) {
                mInvoicesNumber.setText(Integer.toString(response.body().size()));
                Log.v("Responsible",response.body().toString());

            }

            @Override
            public void onFailure(Call<List<Invoice>> call, Throwable t) {
                Toast.makeText(getContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
   private void getUsersById() {
        Call<List<User>> buyersId = Utils.getApi().getBuyerId(token);

        buyersId.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.isSuccessful() && response.body() != null) {

                    userData =response.body();

                    //String mail = userData.get(0).getEmail();
                    mBuyerSize.setText(Integer.toString(userData.size()));

                }
            }
            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Toast.makeText(getContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void  getUserInfo(){
        Call<User> call1 = Utils.getApi().getUser(token);
        Log.v("wabebe", call1.request().url().toString());
        call1.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {


                if (response.isSuccessful() && response.body() != null){
                    Log.v("wabebe1", response.body().toString());
                    userInfo= response.body();

                    mUserAddress.setText("1 - Ngong Lane Plaza "); // hard coded
                    mUserEmail.setText(userInfo.getEmail());
                    accUserName.setText(userInfo.getName());
                    mUserPhone.setText("1 -0722000000"); //hardcoded


                }

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(getContext(), "Error"+t.getLocalizedMessage(), Toast.LENGTH_LONG).show();


            }
        });


    }

    public void logout(){
        mLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toast.makeText(getContext(), "logged out", Toast.LENGTH_LONG).show();
                mPreferences.edit().remove("token").commit();
                Intent logoutIntent = new Intent(getContext(), LoginActivity.class);
                startActivity(logoutIntent);

            }
        });
    }

}