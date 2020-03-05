package com.example.factsafrica.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.factsafrica.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.BindView;
import butterknife.ButterKnife;


public class CreateAccountActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.userName)
    EditText mUserName;
    @BindView(R.id.create_account_email)
    EditText mEmailId;
    @BindView(R.id.create_account_password)
    EditText mPassword;
    @BindView(R.id.create_account_confirm_password)
    EditText mConfrimPassword;
    @BindView(R.id.signUpButton)
    Button mSignUpButton;
    @BindView(R.id.go_to_logIn)
    TextView mLogin;
    ProgressBar mProgressBar;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        ButterKnife.bind(this);
        mProgressBar = findViewById(R.id.progressBarCreateAccount);

        mAuth = FirebaseAuth.getInstance();

        createAuthStateListener();
        
        mSignUpButton.setOnClickListener(this);
        mLogin.setOnClickListener(this);

    }

    private void createAuthStateListener() {
        mAuthListener = firebaseAuth -> {
            final FirebaseUser user = firebaseAuth.getCurrentUser();
            if (user != null) {
                Intent intent = new Intent(CreateAccountActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        };
    }

    @Override
    public void onClick(View view) {
        if (view == mLogin) {
            Intent intent = new Intent(CreateAccountActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }
        if (view == mSignUpButton) {
            createNewUser();
        }
    }

    private void createNewUser() {
        final String name = mUserName.getText().toString().trim();
        final String email = mEmailId.getText().toString().trim();
        String password = mPassword.getText().toString().trim();
        String confirmPassword = mConfrimPassword.getText().toString().trim();


        boolean validEmail = isValidEmail(email);
        boolean validName = isValidName(name);
        boolean validPassword = isValidPassword(password, confirmPassword);
        if (!validEmail || !validName || !validPassword) return;

        mProgressBar.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {

                    mProgressBar.setVisibility(View.INVISIBLE);
                    if (task.isSuccessful()) {

                        Toast.makeText(CreateAccountActivity.this, "Registration Successful.",
                                Toast.LENGTH_SHORT).show();
//getting just registered user
                        FirebaseUser userMM = mAuth.getCurrentUser();
                        userMM.sendEmailVerification()
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        Toast.makeText(CreateAccountActivity.this, "Confirmation Email Sent to : "+email,
                                                Toast.LENGTH_SHORT).show();
                                        Toast.makeText(CreateAccountActivity.this, "Verify Your Email to Login",
                                                Toast.LENGTH_LONG).show();

                                    }
                                });


                    }else {
                        mProgressBar.setVisibility(View.INVISIBLE);
                        Toast.makeText(CreateAccountActivity.this, "Authentication failed 1."+task.getException().getMessage(),
                                Toast.LENGTH_LONG).show();
                    }
                });
    }
    private boolean isValidEmail(String email) {
        boolean isGoodEmail =
                (email != null && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches());
        if (!isGoodEmail) {
            mEmailId.setError("Please enter a valid email address");
            return false;
        }
        return isGoodEmail;
    }

    private boolean isValidName(String name) {
        if (name.equals("")) {
            mUserName.setError("Please enter your name");
            return false;
        }
        return true;
    }

    private boolean isValidPassword(String password, String confirmPassword) {
        if (password.length() < 6) {
            mPassword.setError("Please create a password containing at least 6 characters");
            return false;
        } else if (!password.equals(confirmPassword)) {
            mConfrimPassword.setError("Passwords do not match");
            return false;
        }
        return true;
    }
    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
  
}
