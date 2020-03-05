package com.example.factsafrica.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.factsafrica.R;
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

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.mail) EditText mEd1;  //bind email text
    @BindView(R.id.password) EditText mEd2;//bind password text
    @BindView(R.id.googleSignIn)
    Button signInWithGoogle;
    private GoogleSignInClient mGoogleSigInClient;
    //private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private int RC_SIGN_IN = 123;



    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);
        findViewById(R.id.textView2).setOnClickListener(this);
        findViewById(R.id.button).setOnClickListener(this);
        signInWithGoogle.setOnClickListener(this);

        auth = FirebaseAuth.getInstance();

        mAuthListener = firebaseAuth -> {
            FirebaseUser user = firebaseAuth.getCurrentUser();
            if (user != null) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }

        };

        GoogleSignInOptions signInOptions= new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSigInClient = GoogleSignIn.getClient(this, signInOptions);
    }

    @Override
    public void onClick(View v) {

        if (v== signInWithGoogle) {
            signIn();
        }

        switch (v.getId()){
            case R.id.textView2:

                finish();

                startActivity(new Intent(this, ForgotPasswordActivity.class));  //switch between activity
                break;

            case R.id.button:

                userLogin();
                break;
        }



    }

    private void signIn() {
            Intent googleSignInIntent = mGoogleSigInClient.getSignInIntent();
            startActivityForResult(googleSignInIntent, RC_SIGN_IN);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RC_SIGN_IN){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }

    }
    private void handleSignInResult(Task<GoogleSignInAccount> task) {
        try {
            GoogleSignInAccount signInAccount = task.getResult(ApiException.class);
            //Toast.makeText(this, "Sign In Successful!", Toast.LENGTH_SHORT).show();
            FirebaseGoogleAuth(signInAccount);

        } catch (ApiException e){
            Toast.makeText(this, "Sign In Unsuccessful. Try Again", Toast.LENGTH_SHORT).show();
        }
    }
    private void FirebaseGoogleAuth(GoogleSignInAccount signInAccount) {
        AuthCredential credential = GoogleAuthProvider.getCredential(signInAccount.getIdToken(), null);
        auth.signInWithCredential(credential).addOnCompleteListener(this, task -> {
            if(task.isSuccessful()){
                Toast.makeText(this, "Sign In Successful!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
                FirebaseUser user = auth.getCurrentUser();
                updateUI(user);
            } else {
                Toast.makeText(this, "Sign In Unsuccessful!", Toast.LENGTH_SHORT).show();
                updateUI(null);
            }        });

    }
    private void updateUI(FirebaseUser user) {
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getApplicationContext());
        if(account!=null){
            String name = account.getDisplayName();
            String givenName = account.getGivenName();
            Toast.makeText(this, "Welcome: "+name, Toast.LENGTH_SHORT).show();
        }
    }

    private void userLogin() {
        String email = mEd1.getText().toString().trim();
        String password = mEd2.getText().toString().trim();
        if (email.isEmpty()) {
            mEd1.setError("Email is required"); //mail validation
            mEd1.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEd1.setError("Please enter a valid email");
            mEd1.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            mEd2.setError("Password is required"); //password validation
            mEd2.requestFocus();
            return;
        }


        if (password.length() < 6) {
            mEd2.setError("Minimum length of password should be 6"); //password length
            mEd2.requestFocus();
            return;
        }

        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    finish();
                    Intent intent = new Intent (LoginActivity.this,MainActivity.class);

                    startActivity(intent);

                }else {Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_SHORT).show();

                }

            }
        });

    }

    @Override
    protected void onStart(){
        super.onStart();
        auth.addAuthStateListener(mAuthListener);

//        if(auth.getCurrentUser()!=null){
//            finish();
//        }
//        startActivity(new Intent(this,MainActivity.class));



    }
    @Override
    public void onStop() {
        super.onStop();
        auth.removeAuthStateListener(mAuthListener);
    }




}
