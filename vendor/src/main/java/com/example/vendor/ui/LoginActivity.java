package com.example.vendor.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.vendor.Constants;
import com.example.vendor.R;
import com.example.vendor.models.Login;
import com.example.vendor.models.User;
import com.example.vendor.network.FactsAfricaApi;
import com.example.vendor.network.FactsAfricaClient;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.mail)
    EditText mEmail;
    @BindView(R.id.password) EditText mPassword;
    @BindView(R.id.login)
    Button mLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        mLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mLogin) {
            login(mEmail.getText().toString(), mPassword.getText().toString());
        }
    }

    private void login(String email, String password) {
        Login login = new Login(email, password);
        FactsAfricaApi service = FactsAfricaClient.getClient().create(FactsAfricaApi.class);
        Call<User> call = service.login(login, Constants.ACCEPT_TYPE);
        Log.v("MY URL", call.request().url().toString());
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(LoginActivity.this, "Welcome " + response.body().getFirstName(), Toast.LENGTH_SHORT).show();
                    String token = response.body().getApiToken();
                    Intent homeIntent = new Intent(LoginActivity.this, BottomNavigation.class);
                    homeIntent.putExtra("token", token);
                    startActivity(homeIntent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "Invalid Email or Password", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Network Error!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
