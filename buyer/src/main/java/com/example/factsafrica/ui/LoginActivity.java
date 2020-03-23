package com.example.factsafrica.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.factsafrica.R;
import com.example.factsafrica.ui.models.LoginBuyer;
import com.example.factsafrica.ui.models.User;
import com.example.factsafrica.ui.network.FactsAfricaApi;
import com.example.factsafrica.ui.network.FactsAfricaClient;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    //@BindView(R.id.go_to_create_account) TextView mGoToCreateAccount;
    @BindView(R.id.mail) EditText mEd1;  //bind email text
    @BindView(R.id.password) EditText mEd2;//bind password text
    @BindView(R.id.go_to_create_acc) TextView mGoBack;
//    @BindView(R.id.googleSignIn)
//    Button signInWithGoogle;
    ProgressBar mProgressBarLogin;
    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;
    SharedPreferences sharedpreferences;
    private int RC_SIGN_IN = 123;



    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);
        mProgressBarLogin = findViewById(R.id.progressBarLoginPage);
        findViewById(R.id.textView2).setOnClickListener(this);
        findViewById(R.id.button).setOnClickListener(this);
        mGoBack.setOnClickListener(this);
        //signInWithGoogle.setOnClickListener(this);
        //mGoToCreateAccount.setOnClickListener(this);

//        auth = FirebaseAuth.getInstance();


    }

    @Override
    public void onClick(View v) {

        if(v==mGoBack){
            Intent intent = new Intent(LoginActivity.this, NewUserActivity.class);
            startActivity(intent);
        }

//        if (v== signInWithGoogle) {
//            mProgressBarLogin.setVisibility(View.VISIBLE);
//            signIn();
//        }
//
//        if(v==mGoToCreateAccount){
//            Intent intent = new Intent(LoginActivity.this, CreateAccountActivity.class);
//            startActivity(intent);
//        }

        switch (v.getId()){
            case R.id.textView2:

                finish();

                startActivity(new Intent(this, ForgotPasswordActivity.class));  //switch between activity
                break;

            case R.id.button:
                mProgressBarLogin.setVisibility(View.VISIBLE);
                login(mEd1.getText().toString(), mEd2.getText().toString());
                addEmailToSharedPreferences(mEd1.getText().toString());
                break;
        }



    }


    private void login(String email, String password) {
        LoginBuyer login = new LoginBuyer(email, password);
        FactsAfricaApi service = FactsAfricaClient.getClient().create(FactsAfricaApi.class);
        Call<User> call = service.login(login, ConstantsBuyer.ACCEPT_TYPE);
        Log.v("MY URL", call.request().url().toString());
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    mProgressBarLogin.setVisibility(View.INVISIBLE);
                    Toast.makeText(LoginActivity.this, "Karibu " + response.body().getName(), Toast.LENGTH_SHORT).show();
                    String token = response.body().getApiToken();
                    String bearerToken = "Bearer " + token;
                    mPreferences = PreferenceManager.getDefaultSharedPreferences(LoginActivity.this);
                    mEditor = mPreferences.edit();
                    mEditor.putString("token", bearerToken);
                    mEditor.apply();
                    Intent homeIntent = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(homeIntent);
                    finish();
                } else {
                    mProgressBarLogin.setVisibility(View.INVISIBLE);
                    Toast.makeText(LoginActivity.this, "Invalid Email or Password", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                mProgressBarLogin.setVisibility(View.INVISIBLE);
                Toast.makeText(LoginActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void addEmailToSharedPreferences(String email){
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(ConstantsBuyer.PREFERENCES_EMAIL_KEY, email).apply();
    }







}
