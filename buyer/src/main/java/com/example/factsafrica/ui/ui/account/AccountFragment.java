package com.example.factsafrica.ui.ui.account;

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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.factsafrica.R;
import com.example.factsafrica.ui.ConstantsBuyer;
import com.example.factsafrica.ui.LoginActivity;
import com.example.factsafrica.ui.models.Invoice;
import com.example.factsafrica.ui.models.User;
import com.example.factsafrica.ui.network.FactsAfricaApi;
import com.example.factsafrica.ui.network.FactsAfricaClient;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.constraintlayout.widget.Constraints.TAG;


public class AccountFragment extends Fragment {


    private String mEmail;
    @BindView(R.id.account_user_name)
    TextView accUserName;
    @BindView(R.id.acc_user_email)
    TextView mUserEmail;
    @BindView(R.id.buyer_size)
    TextView mBuyerSize;
    @BindView(R.id.total_invoices_sent)
    TextView mInvoicesNumber;
    @BindView(R.id.buyerAddress)
    TextView  mUserAddress;
    @BindView(R.id.buyerPhone)
    TextView  mUserPhone;
    @BindView(R.id.logout_image)
    ImageView mLogout;
    private String token;
    private SharedPreferences mPreferences;

    private AccountViewModel accountViewModel;
    User userInfo;

    @Override
    public void onStart() {
        super.onStart();
        getAllInvoices();
        getAllBuyers();
        getUserInfo();
        logout();
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        accountViewModel =
                ViewModelProviders.of(this).get(AccountViewModel.class);
        View root = inflater.inflate(R.layout.fragment_account, container, false);
        mPreferences = PreferenceManager.getDefaultSharedPreferences(root.getContext());
        mEmail = mPreferences.getString(ConstantsBuyer.PREFERENCES_EMAIL_KEY, null);
        Log.d(TAG, "onCreate: "+ mEmail);
        ButterKnife.bind(this, root);
        token = mPreferences.getString("token", "");
        Log.v("passed token", token);
        return root;
    }

    private void getAllInvoices() {

        FactsAfricaApi service = FactsAfricaClient.getClient().create(FactsAfricaApi.class);
        Call<List<Invoice>> call = service.getAllInvoices(token);
        Log.v("URL", call.request().url().toString());
        call.enqueue(new Callback<List<Invoice>>() {
            @Override
            public void onResponse(Call<List<Invoice>> call, Response<List<Invoice>> response) {
                mInvoicesNumber.setText(Integer.toString(response.body().size()));
            }

            @Override
            public void onFailure(Call<List<Invoice>> call, Throwable t) {
                Toast.makeText(getContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getAllBuyers(){
        FactsAfricaApi service = FactsAfricaClient.getClient().create(FactsAfricaApi.class);
        Call<List<User>> call = service.getVendors(token);
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                mBuyerSize.setText(Integer.toString(response.body().size()));
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {

            }
        });
    }

    //

    public void  getUserInfo(){
        FactsAfricaApi service = FactsAfricaClient.getClient().create(FactsAfricaApi.class);

        Call<User> call1 = service.getUser(token);
        Log.v("wabebe", call1.request().url().toString());
        call1.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()&& response.body()!= null){

                    userInfo= response.body();

                    mUserAddress.setText("1 - Ngong Lane Plaza "); // hard coded
                    mUserEmail.setText(userInfo.getEmail());
                    accUserName.setText(userInfo.getName());
                    mUserPhone.setText("1 -0722000000"); //hardcoded


                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(getContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

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
                getActivity().finish();

            }
        });
    }




}
