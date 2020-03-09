package com.example.factsafrica.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.factsafrica.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPasswordActivity extends AppCompatActivity {

    private Button resetPasswordButton;
    private EditText resetPasswordEditText;
    private TextView mTextBackToLogin;
    private ProgressBar mProgressBar;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        mAuth = FirebaseAuth.getInstance();

        resetPasswordButton = (Button) findViewById(R.id.ResetPasswordButton);
        mTextBackToLogin = findViewById(R.id.forgot_password_back_to_login);
        resetPasswordEditText = (EditText) findViewById(R.id.editTextEmail);
        mProgressBar = findViewById(R.id.progressBarForgotPassword);

        resetPasswordButton.setOnClickListener(v -> {
            mProgressBar.setVisibility(View.VISIBLE);

            String emailText = resetPasswordEditText.getText().toString();

            if(TextUtils.isEmpty(emailText)){
                Toast.makeText(ForgotPasswordActivity.this,"Please write your valid email address first",Toast.LENGTH_SHORT).show();
            }else{
                mAuth.sendPasswordResetEmail(emailText).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        mProgressBar.setVisibility(View.INVISIBLE);

                        if(task.isSuccessful()){
                            Toast.makeText(ForgotPasswordActivity.this,"Please check your email account for a confirmation link",Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(ForgotPasswordActivity.this,LoginActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                            finish();
                        }else {
                            String message = task.getException().getMessage();
                            Toast.makeText(ForgotPasswordActivity.this,"Error occurred",Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }

        });

        mTextBackToLogin.setOnClickListener(v -> {
            Intent intent = new Intent(ForgotPasswordActivity.this, LoginActivity.class);
            startActivity(intent);
        });
    }
}