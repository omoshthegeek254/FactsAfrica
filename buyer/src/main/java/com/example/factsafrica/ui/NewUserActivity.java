package com.example.factsafrica.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.factsafrica.R;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewUserActivity extends AppCompatActivity {

    @BindView(R.id.createAccResetButton) Button resetPasswordButton;
    @BindView(R.id.go_to_create_acc) TextView mCreateAcc;
    private EditText resetPasswordEditText;
    private TextView mTextBackToLogin;
    private ProgressBar mProgressBar;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);
        ButterKnife.bind(this);

        mAuth = FirebaseAuth.getInstance();
        mTextBackToLogin = findViewById(R.id.new_user_back_to_login);
        resetPasswordEditText = findViewById(R.id.editTextEmailCreate);
        mProgressBar = findViewById(R.id.progressBarNewPassword);

        resetPasswordButton.setOnClickListener(v -> {
            mProgressBar.setVisibility(View.VISIBLE);

            String emailText = resetPasswordEditText.getText().toString();

            if(TextUtils.isEmpty(emailText)){
                Toast.makeText(NewUserActivity.this,"Please write your valid email address first",Toast.LENGTH_SHORT).show();
            }else{
                mAuth.sendPasswordResetEmail(emailText).addOnCompleteListener(task -> {
                    mProgressBar.setVisibility(View.INVISIBLE);

                    if(task.isSuccessful()){
                        Toast.makeText(NewUserActivity.this,"Please check your email account for a confirmation link",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(NewUserActivity.this,LoginActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        finish();
                    }else {
                        String message = task.getException().getMessage();
                        Toast.makeText(NewUserActivity.this,"Error occurred",Toast.LENGTH_SHORT).show();
                    }

                });
            }

        });

        mTextBackToLogin.setOnClickListener(v -> {
            Intent intent = new Intent(NewUserActivity.this, LoginActivity.class);
            startActivity(intent);
        });
    }

}